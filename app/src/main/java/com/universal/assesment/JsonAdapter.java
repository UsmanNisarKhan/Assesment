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

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.ViewDetails> {

    ArrayList<NameModel> arrayList;
    Context context;
    Activity activity;

    public JsonAdapter(ArrayList<NameModel> arrayList, Context context, Activity activity) {
        this.arrayList = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.json_items,parent,false);
        return new ViewDetails(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDetails holder, int position) {

        holder.textView.setText(arrayList.get(position).getName());

        if(holder.textView.getText().equals("Research Article"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context,BookDetails.class).putExtra("book","1"));
                    activity.finish();

                }
            });
        }
        else if(holder.textView.getText().equals("Research Article 2"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context,BookDetails.class).putExtra("book","2"));
                    activity.finish();
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewDetails extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewDetails(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textview);
        }
    }
}
