package com.example.huylvph08000_adroidnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huylvph08000_adroidnetworking.model.Flickr;
import com.example.huylvph08000_adroidnetworking.model.Image;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import dmax.dialog.SpotsDialog;
import okhttp3.internal.Util;
import retrofit2.http.Url;

public class DetailImageActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1000 ;
    FloatingActionButton fab, fab1, fab2, fab3;
    public ViewPager viewPager;
    ViewpagerAdapter viewpagerAdapter;
    TextView tv1, tv2, tv3;
    boolean An_Hien = false;
    AlertDialog dialog;
    String link;
    String idImage;
    public int position;
    public String sv;
    List<Image> photos;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Ðã được cho phép", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Quyền bị từ chối", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        tv1 = findViewById(R.id.tv1);
        fab2 = findViewById(R.id.fab2);
        tv2 = findViewById(R.id.tv2);
        fab3 = findViewById(R.id.fab3);
        tv3 = findViewById(R.id.tv3);

        viewPager = findViewById(R.id.viewPager);
        Intent intent = this.getIntent();
        position = intent.getIntExtra("position", 0);
        sv = intent.getStringExtra("service");
        getData();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            },PERMISSION_REQUEST_CODE);

        //
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (An_Hien == false){
                    Hien();
                    An_Hien = true;
                } else {
                    An();
                    An_Hien = false;
                }
            }
        });

        //
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(DetailImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(DetailImageActivity.this, "",Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSION_REQUEST_CODE);
                    return;
                } else {
                    dialog = new SpotsDialog.Builder().setContext(DetailImageActivity.this).build();
                    dialog.show();
                    dialog.setMessage("Downloading...");

                    String fileName = UUID.randomUUID().toString()+".jpg";
                    Picasso.with(getBaseContext())
                            .load(photos.get(viewPager.getCurrentItem()).getUrlL())
                            .into(new ImageSaveHelper(getBaseContext(), dialog,
                                    getApplicationContext().getContentResolver(),
                                    fileName, "Image description"));
                }

            }
        });

        //
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(DetailImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(DetailImageActivity.this, "",Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSION_REQUEST_CODE);
                    return;
                } else {
                    dialog = new SpotsDialog.Builder().setContext(DetailImageActivity.this).build();
                    dialog.show();
                    dialog.setMessage("Setting...");

                   String fileName2 = UUID.randomUUID().toString();

                    Picasso.with(getBaseContext()).load(photos.get(viewPager.getCurrentItem()).getUrlL()).into(new ImageSaveHelper(getBaseContext(),
                            dialog,
                            getApplicationContext().getContentResolver(),
                            fileName2, "Image description2"));
                    WallpaperManager wManager = WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wManager.setBitmap(BitmapFactory.decodeFile("/storage/emulated/0/Pictures/"+fileName2+".jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                i.putExtra(Intent.EXTRA_TEXT, photos.get(viewPager.getCurrentItem()).getUrlL());
                startActivity(Intent.createChooser(i, "Share URL"));
            }
        });

    }


    private void getData() {
        final ProgressDialog loading = new ProgressDialog(DetailImageActivity.this);
        loading.setMessage("Loading...");
        loading.show();
        RequestQueue requestQueue =
                Volley.newRequestQueue(DetailImageActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://www.flickr.com/services/rest", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                Flickr flickrPhoto =
                        gson.fromJson(response, Flickr.class);

                photos = flickrPhoto.getPhotos().getPhoto();

                viewpagerAdapter = new ViewpagerAdapter(DetailImageActivity.this, photos);
                link = photos.get(viewPager.getCurrentItem()).getUrlL();
                idImage = photos.get(viewPager.getCurrentItem()).getId();
                viewPager.setAdapter(viewpagerAdapter);
                viewPager.setCurrentItem(position, true);
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
                viewpagerAdapter.notifyDataSetChanged();
                loading.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(DetailImageActivity.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // lưu giữ các giá trị theo cặp key/value
                Map<String, String> params = new HashMap<>();
                params.put("api_key", "bd17e566558baf694db6424c5ad2b74a");
                params.put("user_id", "187053598@N06");
                params.put("extras", "views, media, path_alias, url_l, url_o");
                params.put("format", "json");
                params.put("method", sv);
                params.put("nojsoncallback", "1");

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void An() {
        fab1.hide();
        fab2.hide();
        fab3.hide();
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);

    }

    private void Hien() {
        fab1.show();
        fab2.show();
        fab3.show();
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
    }





}
