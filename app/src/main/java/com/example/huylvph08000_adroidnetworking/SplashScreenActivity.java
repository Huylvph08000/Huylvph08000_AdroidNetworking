package com.example.huylvph08000_adroidnetworking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    Intent intent=new Intent(getBaseContext(),NewFeedActivity.class);
                    startActivity(intent);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }
}