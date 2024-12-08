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

public class Food extends AppCompatActivity {
    ProgressDialog progressDialog;
    private FoodAdapter adapter;
    private RecyclerView recyclerView;
    private FoodJsonPlaceHolderAPI FoodJsonPlaceholderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        progressDialog = new ProgressDialog(Food.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.food)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FoodJsonPlaceholderAPI = retrofit.create(FoodJsonPlaceHolderAPI.class);
        getPosts();
    }

    private void getPosts(){
        Call<List<FoodPost>> call = FoodJsonPlaceholderAPI.getPosts();
        call.enqueue(new Callback<List<FoodPost>>(){

            @Override
            public void onResponse(Call<List<FoodPost>> call, Response<List<FoodPost>> response) {
                if(!response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(Food.this, "Error !", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<FoodPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Food.this, "Error Buka!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<FoodPost> foodPosts){
        recyclerView = findViewById(R.id.foodRecyclerView);
        adapter = new FoodAdapter(this,foodPosts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Food.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}