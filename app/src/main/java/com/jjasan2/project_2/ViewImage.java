package com.jjasan2.project_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ViewImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        Intent intent = getIntent();
        int imgSource = intent.getIntExtra("imgSource", 0);

        ImageView imgView = findViewById(R.id.fullscreen_img);
        imgView.setImageResource(imgSource);
    }
}