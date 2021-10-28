package com.universal.assesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.universal.assesment.databinding.ActivityJsonBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonActivity extends AppCompatActivity {

    ArrayList<NameModel> nameModels = new ArrayList<NameModel>();
    ActivityJsonBinding activityJsonBinding;
    JsonAdapter jsonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJsonBinding = ActivityJsonBinding.inflate(getLayoutInflater());
        setContentView(activityJsonBinding.getRoot());

        try {
            JSONObject obj = new JSONObject(loadJSON());

            JSONObject userObject = obj.getJSONObject("response");
            JSONArray userArray = userObject.getJSONArray("docs");

            for (int i = 0; i < userArray.length(); i++) {

                JSONObject jsonObject = userArray.getJSONObject(i);
                jsonObject.getString("article_name");

                JSONObject json_Object = userArray.getJSONObject(i+1);
                json_Object.getString("article_name");

                nameModels.add(new NameModel(jsonObject.getString("article_name")));
                nameModels.add(new NameModel(json_Object.getString("article_name")));


                }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        jsonAdapter = new JsonAdapter(nameModels,this,this);
        activityJsonBinding.recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        activityJsonBinding.recyclerView1.setAdapter(jsonAdapter);


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

        startActivity(new Intent(JsonActivity.this,MainActivity.class).putExtra("id","frame1"));
        finish();
    }

}