package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menuutama extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);
    }

    //pindah ke menu food
    public void pindah_menufood(View view){
        Intent i = new Intent(Menuutama.this, Food.class);
        startActivity(i);
    }

    public void pindah_menuKendaraan(View view){
        Intent i = new Intent(Menuutama.this, Kendaraan.class);
        startActivity(i);
    }

    public void pindah_menuInformation(View view){
        Intent i = new Intent(Menuutama.this, Information.class);
        startActivity(i);
    }

    public void pindah_menuHotel(View view){
        Intent i = new Intent(Menuutama.this, Hotel.class);
        startActivity(i);
    }
}