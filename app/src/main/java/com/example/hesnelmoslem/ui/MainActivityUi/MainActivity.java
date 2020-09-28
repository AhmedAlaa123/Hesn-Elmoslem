package com.example.hesnelmoslem.ui.MainActivityUi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.example.hesnelmoslem.R;
import com.example.hesnelmoslem.ui.MenuActivity.MenuActivity;


public class MainActivity extends AppCompatActivity  {

private ImageView imageView_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView_logo=findViewById(R.id.imageView_logo);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.logo_anim);
        imageView_logo.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }





}