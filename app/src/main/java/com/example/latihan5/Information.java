package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Information extends AppCompatActivity {
    ProgressDialog progressDialog;
    private InformationAdapter adapter;
    private RecyclerView recyclerView;
    private InformationJsonPlaceHolderAPI InformationJsonPlaceholderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    progressDialog = new ProgressDialog(Information.this);
    progressDialog.setMessage("Loading....");
    progressDialog.show();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url.information)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    InformationJsonPlaceholderAPI = retrofit.create(InformationJsonPlaceHolderAPI.class);
    getPosts();
    }

    private void getPosts(){
        Call<List<InformationPost>> call = InformationJsonPlaceholderAPI.getPosts();
        call.enqueue(new Callback<List<InformationPost>>(){

            @Override
            public void onResponse(Call<List<InformationPost>> call, Response<List<InformationPost>> response) {
                if(!response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(Information.this, "Error !", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<InformationPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Information.this, "Error Buka!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<InformationPost> informationPosts){
        recyclerView = findViewById(R.id.informationRecyclerView);
        adapter = new InformationAdapter(this,informationPosts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Information.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void tambah(View view){
        Intent i = new Intent(Information.this, tambah_informasi.class);
        startActivity(i);
    }

}

