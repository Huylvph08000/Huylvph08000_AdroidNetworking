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
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(5*1000);

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