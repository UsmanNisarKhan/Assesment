package com.universal.assesment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.universal.assesment.databinding.ActivityFrameBinding;

public class FrameActivity extends AppCompatActivity {

    ActivityFrameBinding activityFrameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFrameBinding = ActivityFrameBinding.inflate(getLayoutInflater());
        setContentView(activityFrameBinding.getRoot());

        Intent intent = getIntent();

        if(intent.getStringExtra("id").equals("frame1")){

            activityFrameBinding.frame.setImageResource(R.drawable.frame1);

            ImageView myImage = new ImageView(this);
            myImage.setImageResource(R.drawable.plus1);
            FrameLayout.LayoutParams myImageLayout = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
            myImageLayout.gravity = Gravity.CENTER;
            myImageLayout.topMargin = getResources().getDimensionPixelSize(R.dimen._75sdp);
            myImage.setLayoutParams(myImageLayout);

            activityFrameBinding.frameLayout.addView(myImage);

        }
        else if(intent.getStringExtra("id").equals("frame2")){

            activityFrameBinding.frame.setImageResource(R.drawable.frame2);

            ImageView myImage = new ImageView(this);
            myImage.setImageResource(R.drawable.plus1);
            FrameLayout.LayoutParams myImageLayout = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
            myImageLayout.gravity = Gravity.CENTER;
            myImageLayout.leftMargin = getResources().getDimensionPixelSize(R.dimen._55sdp);
            myImage.setLayoutParams(myImageLayout);

            activityFrameBinding.frameLayout.addView(myImage);
        }
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(FrameActivity.this,MainActivity.class).putExtra("id","frame1"));
        finish();
    }
}