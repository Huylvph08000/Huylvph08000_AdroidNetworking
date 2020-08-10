package com.example.huylvph08000_adroidnetworking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FavoriteImageActivity extends AppCompatActivity {
    ImageViewAdapter imageViewAdapter;
    RecyclerView recyclerViewImage;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final int NUM_COLUMNS = 2;
    private String service = "flickr.favorites.getList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My favorite images");
        recyclerViewImage = findViewById(R.id.recyclerview);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GetData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        GetData();
    }

    private void GetData() {
        swipeRefreshLayout.setRefreshing(true);

        RequestQueue requestQueue =
                Volley.newRequestQueue(FavoriteImageActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://www.flickr.com/services/rest", new Response.Listener<String>() { //Nơi bạn nhận dữ liệu trả về từ server khi request hoàn thành
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                Flickr flickrPhoto =
                        gson.fromJson(response, Flickr.class);

                List<Image> photos = flickrPhoto.getPhotos().getPhoto();

                imageViewAdapter = new ImageViewAdapter(getApplication(), (ArrayList<Image>) photos,
                        new ImageViewAdapter.AdapterListener() {
                            @Override
                            public void OnClick(int position) {
                                Intent intent = new Intent(FavoriteImageActivity.this, DetailImageActivity.class);
                                intent.putExtra("position",position);
                                intent.putExtra("service", service);
                                startActivity(intent);
                            }
                        });
                StaggeredGridLayoutManager staggeredGridLayoutManager = new
                        StaggeredGridLayoutManager(NUM_COLUMNS,LinearLayoutManager.VERTICAL);
                recyclerViewImage.setLayoutManager(staggeredGridLayoutManager);
                recyclerViewImage.setAdapter(imageViewAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(FavoriteImageActivity.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", "bd17e566558baf694db6424c5ad2b74a");
                params.put("user_id", "187053598@N06");
                params.put("extras", "views, media, path_alias, url_l, url_o");
                params.put("format", "json");
                params.put("method", service);
                params.put("nojsoncallback", "1");

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
