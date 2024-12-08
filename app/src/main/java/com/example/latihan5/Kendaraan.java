package com.example.latihan5;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Kendaraan extends AppCompatActivity {
    ProgressDialog progressDialog;
    private KendaraanAdapter adapter;
    private RecyclerView recyclerView;
    private KendaraanJsonPlaceHolderAPI KendaraanJsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kendaraan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressDialog = new ProgressDialog(Kendaraan.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.kendaraan)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KendaraanJsonPlaceHolderAPI = retrofit.create(KendaraanJsonPlaceHolderAPI.class);
        getPosts();
    }

    private void getPosts(){
        Call<List<KendaraanPost>> call = KendaraanJsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<KendaraanPost>>(){

            @Override
            public void onResponse(Call<List<KendaraanPost>> call, Response<List<KendaraanPost>> response) {
                if(!response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(Kendaraan.this, "Error !", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<KendaraanPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Kendaraan.this, "Error Buka!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<KendaraanPost> kendaraanPosts){
        recyclerView = findViewById(R.id.KendaraanRecyclerView);
        adapter = new KendaraanAdapter(this,kendaraanPosts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Kendaraan.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}