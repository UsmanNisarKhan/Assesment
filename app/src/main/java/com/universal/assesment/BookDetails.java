package com.universal.assesment;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.universal.assesment.databinding.ActivityBookDetailsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BookDetails extends AppCompatActivity {

    ActivityBookDetailsBinding activityBookDetailsBinding;
    DetailsAdapter detailsAdapter;
    ArrayList<JsonModel> jsonModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookDetailsBinding = ActivityBookDetailsBinding.inflate(getLayoutInflater());
        setContentView(activityBookDetailsBinding.getRoot());

        Intent intent = getIntent();

        if (intent.getStringExtra("book").equals("1")) {

            showResults("books1");
        }
        if (intent.getStringExtra("book").equals("2")) {

            showResults("books2");

        }
    }

    public String loadJSON() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(BookDetails.this,MainActivity.class).putExtra("id","frame1"));
        finish();
    }

    public void showResults(String name)
    {
        try {
            JSONObject obj = new JSONObject(loadJSON());

            JSONObject userObject = obj.getJSONObject("response");
            JSONArray userArray = userObject.getJSONArray("docs");

            for (int i = 0; i < userArray.length(); i++) {

                JSONObject jsonObject = null;

                if(name.equals("books1"))
                {
                     jsonObject = userArray.getJSONObject(i);

                }else if(name.equals("books2")){

                     jsonObject = userArray.getJSONObject(i+1);
                }

                JSONArray jsonArray1 = jsonObject.getJSONArray(name);

                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                    JSONArray jsonArray2 = jsonObject1.getJSONArray("book_names");

                    for (int k = 0; k < jsonArray2.length(); k++) {
                        JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
                        jsonObject2.getString("author");
                        jsonObject2.getString("language");

                        Log.i("tag", jsonObject2.getString("author") + " " +
                                jsonObject2.getString("language"));


                        jsonModels.add(new JsonModel(jsonObject2.getString("language"), jsonObject2.getString("author"), "Books1"));

                        detailsAdapter = new DetailsAdapter(jsonModels, this, this);
                        activityBookDetailsBinding.recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                        activityBookDetailsBinding.recyclerView2.setAdapter(detailsAdapter);
                    }

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}