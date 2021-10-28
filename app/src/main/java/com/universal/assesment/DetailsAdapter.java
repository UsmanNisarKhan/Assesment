package com.universal.assesment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


    public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewDetail> {

        ArrayList<JsonModel> arrayList;
        Context context;
        Activity activity;

        public DetailsAdapter(ArrayList<JsonModel> arrayList, Context context, Activity activity) {
            this.arrayList = arrayList;
            this.context = context;
            this.activity = activity;
        }

        @NonNull
        @Override
        public DetailsAdapter.ViewDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.details_items,parent,false);
            return new DetailsAdapter.ViewDetail(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DetailsAdapter.ViewDetail holder, int position) {

                holder.author.setText(arrayList.get(position).getAuthor());
                holder.language.setText(arrayList.get(position).getLanguage());

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ViewDetail extends RecyclerView.ViewHolder {

            TextView author,language;

            public ViewDetail(@NonNull View itemView) {
                super(itemView);

                author = itemView.findViewById(R.id.author);
                language = itemView.findViewById(R.id.language);
            }
        }
    }
