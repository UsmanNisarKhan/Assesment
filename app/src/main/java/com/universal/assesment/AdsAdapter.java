package com.universal.assesment;

import static com.google.android.gms.ads.formats.NativeAdOptions.ADCHOICES_TOP_RIGHT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.AdChoicesView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;


public class AdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ImageModel> mData;
    private LayoutInflater mInflater;
    Context context;
    private NativeAd nativeAd;
    Activity activity;
    int VIEW_TYPE_ITEM = 0;
    int VIEW_TYPE_LOADING = 1;

    AdsAdapter(Context context, Activity activity, ArrayList<ImageModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context=context;
        this.activity=activity;
    }

    public int getItemViewType(int position)
    {
        if (position  == 2) {
            return VIEW_TYPE_LOADING;
        }else{
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_native_ad_placeholder, parent, false);
            return new Ads(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (holder instanceof MyViewHolder) {

            MyViewHolder mholder = (MyViewHolder) holder;

            mholder.imageView.setImageResource(mData.get(position).getImage());


        } else if (holder instanceof Ads) {

            Ads myholder = (Ads) holder;

            myholder.frameLayout.setVisibility(View.VISIBLE);
            AdLoader.Builder builder = new AdLoader.Builder(activity, activity.getResources().getString(R.string.admob_native_id));
            NativeAdView defaultAdview = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.native_ad_media_lay, null);
            ShimmerFrameLayout container = defaultAdview.findViewById(R.id.shimmer);

            builder.forNativeAd(mNativeAd -> {
                Log.i("NativeAdLogs", "Large Native Ad Loaded 1st time");
                if (nativeAd != null) {
                    nativeAd.destroy();
                }

                nativeAd = mNativeAd;

                    populateSmallNativeAdView(nativeAd,defaultAdview);
                    container.hideShimmer();

                myholder.frameLayout.removeAllViews();
                myholder.frameLayout.addView(defaultAdview);

            });


            VideoOptions videoOptions = new VideoOptions.Builder()
                    .setStartMuted(false)
                    .build();

            NativeAdOptions adOptions = new NativeAdOptions.Builder()
                    .setVideoOptions(videoOptions)
                    .setAdChoicesPlacement(ADCHOICES_TOP_RIGHT)
                    .build();

            builder.withNativeAdOptions(adOptions);

            AdLoader adLoader = builder.withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError errorCode) {
                    Log.i("AdUtils", "failed to load NativeAd error code: " + errorCode);
                    myholder.frameLayout.setVisibility(View.GONE);
                }

            }).build();
            adLoader.loadAd(new AdRequest.Builder().build());


        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
        }

    }

    public class Ads extends RecyclerView.ViewHolder {

        FrameLayout frameLayout;

        Ads(View view) {
            super(view);

            frameLayout = view.findViewById(R.id.native_frag);

        }
    }


    private void populateSmallNativeAdView(NativeAd nativeAd, NativeAdView adView) {

        adView.setHeadlineView(adView.findViewById(R.id.ad_title));

        adView.setBodyView(adView.findViewById(R.id.ad_body));

        adView.setCallToActionView(adView.findViewById(R.id.ad_open));

        adView.setIconView(adView.findViewById(R.id.ad_app_icon));


        // The headline is guaranteed to be in every NativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else{
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }


        if (nativeAd.getAdChoicesInfo() != null) {
            AdChoicesView choicesView = new AdChoicesView(adView.getContext());
            adView.setAdChoicesView(choicesView);
        }
        adView.setNativeAd(nativeAd);
    }

}