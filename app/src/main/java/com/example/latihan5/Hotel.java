package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Hotel extends AppCompatActivity {
    ProgressDialog progressDialog;
    private HotelAdapter adapter;
    private RecyclerView recyclerView;
    private HotelJsonPlaceHolderAPI HotelJsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        progressDialog = new ProgressDialog(Hotel.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.hotel)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HotelJsonPlaceHolderAPI = retrofit.create(HotelJsonPlaceHolderAPI.class);
        getPosts();
    }

    private void getPosts(){
        Call<List<HotelPost>> call = HotelJsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<HotelPost>>(){

            @Override
            public void onResponse(Call<List<HotelPost>> call, Response<List<HotelPost>> response) {
                if(!response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(Hotel.this, "Error !", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<HotelPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Hotel.this, "Error Buka!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<HotelPost> hotelPosts){
        recyclerView = findViewById(R.id.hotelRecyclerView);
        adapter = new HotelAdapter(this,hotelPosts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Hotel.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}