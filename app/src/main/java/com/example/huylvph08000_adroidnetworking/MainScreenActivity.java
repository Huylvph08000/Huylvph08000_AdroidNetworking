package com.example.huylvph08000_adroidnetworking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_images, menu);
        inflater.inflate(R.menu.updated_images, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_favorite_images:
                intent = new Intent(this, FavoriteImageActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_updated_images:
                intent = new Intent(this, ImageUploadedActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}