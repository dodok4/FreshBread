package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminEkraniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ekrani);

        Button btn_urunekleme=(Button)findViewById(R.id.btn_urunekleme);
        Button btn_gunceldir=(Button)findViewById(R.id.btn_gunceldir);
        Button btn_ara=(Button)findViewById(R.id.btn_ara);


        btn_ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aurunguncellegec=new Intent(getApplicationContext(),AdminAramaEkraniActivity.class);
                startActivity(aurunguncellegec);

            }
        });


        btn_gunceldir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aurunguncellegec=new Intent(getApplicationContext(),AdminUrunGuncelle.class);
                startActivity(aurunguncellegec);

            }
        });



        btn_urunekleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent auruneklemegec=new Intent(getApplicationContext(),AdminUrunEklemeActivity.class);
                startActivity(auruneklemegec);



            }
        });
    }
}
