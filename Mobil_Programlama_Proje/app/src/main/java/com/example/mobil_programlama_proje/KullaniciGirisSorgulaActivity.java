package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.ClosedWatchServiceException;
import java.util.ArrayList;

public class KullaniciGirisSorgulaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_giris_sorgula);

        final ArrayList<String> kullaniciadibulundu = new ArrayList<>();
      final EditText et_kullaniciadial=(EditText)findViewById(R.id.et_kullaniciadial);
      final EditText et_kullanicisifreal =(EditText)findViewById(R.id.et_kullanicisifreal);

      Button btn_kullanicigirisal =(Button)findViewById(R.id.btn_kullanicigirisal);

        final Veritabani db= new Veritabani(KullaniciGirisSorgulaActivity.this);

      btn_kullanicigirisal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String kullaniciadi = et_kullaniciadial.getText().toString();
              String sifre = et_kullanicisifreal.getText().toString();


             Cursor data = db.girisSorgula(kullaniciadi, sifre);
            kullaniciadibulundu.clear();



              if (data.getCount() == 0){

                  et_kullaniciadial.setText("");
                  et_kullanicisifreal.setText("");

                  Toast.makeText(getApplicationContext(),"Girdiğiniz kullanıcı adı veya şifre hatalı!",Toast.LENGTH_LONG).show();
              }
              else{

                  while (data.moveToNext()) {
                      kullaniciadibulundu.add(data.getString(0));
                  }
                 Intent kullaniciekraninagecis=new Intent(getApplicationContext(),KullaniciEkrani.class);
                  kullaniciekraninagecis.putExtra("kullaniciadi",kullaniciadibulundu.get(0));
                  startActivity(kullaniciekraninagecis);


              }







          }
      });





    }
}
