package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class KullaniciEkrani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_ekrani);


        Button btn_bakiyeyukle=(Button)findViewById(R.id.btn_bakiyeyukle);
        Button btn_siparisver=(Button)findViewById(R.id.btn_siparisver);
        Button btn_geriiade=(Button)findViewById(R.id.btn_geriiade);

        Button btn_paratransferi=(Button)findViewById(R.id.btn_paratransferi);


        btn_siparisver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =getIntent();
                String kullaniciadial=intent.getStringExtra("kullaniciadi");



                Intent yuklegec=new Intent(getApplicationContext(),SiparisVerActivity.class);
                yuklegec.putExtra("kullan",kullaniciadial);
                startActivity(yuklegec);



            }
        });

        btn_geriiade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =getIntent();
                String kullaniciadial=intent.getStringExtra("kullaniciadi");



                Intent yuklegec=new Intent(getApplicationContext(),UrunIadeActivity.class);
                yuklegec.putExtra("kullan",kullaniciadial);
                startActivity(yuklegec);



            }
        });

        btn_paratransferi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =getIntent();
                String kullaniciadial=intent.getStringExtra("kullaniciadi");



                Intent yuklegec=new Intent(getApplicationContext(),BakiyeTransferActivity.class);
                yuklegec.putExtra("kullan",kullaniciadial);
                startActivity(yuklegec);

            }
        });


        btn_bakiyeyukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent =getIntent();
             String kullaniciadial=intent.getStringExtra("kullaniciadi");



             Intent yuklegec=new Intent(getApplicationContext(),BakiyeYuklemeEkraniActivity.class);
             yuklegec.putExtra("kullan",kullaniciadial);
             startActivity(yuklegec);

            }
        });



    }
}
