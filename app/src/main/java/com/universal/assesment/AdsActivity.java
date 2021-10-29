package com.universal.assesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.universal.assesment.databinding.ActivityAdsBinding;

import java.util.ArrayList;

public class AdsActivity extends AppCompatActivity {

    AdsAdapter adsAdapter;
    ActivityAdsBinding activityAdsBinding;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdsBinding = ActivityAdsBinding.inflate(getLayoutInflater());
        setContentView(activityAdsBinding.getRoot());

        ArrayList<ImageModel> imageModels = new ArrayList<>();
        imageModels.add(new ImageModel(R.drawable.frame1));
        imageModels.add(new ImageModel(R.drawable.frame2));
        imageModels.add(new ImageModel(R.drawable.frame1));
        imageModels.add(new ImageModel(R.drawable.frame2));
        imageModels.add(new ImageModel(R.drawable.frame1));
        imageModels.add(new ImageModel(R.drawable.frame2));
        imageModels.add(new ImageModel(R.drawable.frame1));
        imageModels.add(new ImageModel(R.drawable.frame2));

        adsAdapter = new AdsAdapter(this,this, imageModels);
        gridLayoutManager = new GridLayoutManager(AdsActivity.this, 2);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(adsAdapter.getItemViewType(position)){
                    case 0:
                        return 1;
                    case 1:
                        return 2;
                    default:
                        return -1;
                }
            }
        });
        activityAdsBinding.recyclerView3.setLayoutManager(gridLayoutManager);
        activityAdsBinding.recyclerView3.setAdapter(adsAdapter);

    }
}