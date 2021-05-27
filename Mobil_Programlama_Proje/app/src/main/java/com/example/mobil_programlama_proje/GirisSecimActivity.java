package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirisSecimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_secim);

        Button btn_kullanicisecim=(Button)findViewById(R.id.btn_kullanicisecim);
        Button btn_admin=(Button)findViewById(R.id.btn_admin);



        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminsayfasinagecis=new Intent(getApplicationContext(),YoneticiGirisSorgulaActivity.class);
                startActivity(adminsayfasinagecis);


            }
        });

        btn_kullanicisecim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),KullaniciGirisSorgulaActivity.class);
                startActivity(intent);
            }
        });

    }
}
