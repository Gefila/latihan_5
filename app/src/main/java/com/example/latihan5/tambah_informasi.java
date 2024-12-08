package com.example.latihan5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class tambah_informasi extends AppCompatActivity {

    private EditText email,komentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambah_informasi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.informationEmail);
        komentar = findViewById(R.id.informationKomentar);
    }

    public void tambah_data (View view){
        final String email1 = email.getText().toString().trim();
        final String komentar1 = komentar.getText().toString().trim();

        class tambah_data extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(tambah_informasi.this, "Add", "Wait", false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("email", email1);
                params.put("komentar", komentar1);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(url.tambah_informasi, params);
                return res;
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();

                if(s.equals("berhasil")){
                    Intent i = new Intent(tambah_informasi.this, Information.class);
                    startActivity(i);
                }else{
                    Toast.makeText(tambah_informasi.this, "Data harus lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        }
        tambah_data ae = new tambah_data();
        ae.execute();
    }
}