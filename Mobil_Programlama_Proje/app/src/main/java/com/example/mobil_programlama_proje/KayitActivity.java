package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class KayitActivity extends AppCompatActivity {

    int id1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);



        final EditText et_ad=(EditText)findViewById(R.id.et_ad);
        final EditText et_soyad;
        et_soyad = (EditText)findViewById(R.id.et_soyad);
        final EditText et_telefon=(EditText)findViewById(R.id.et_telefon);
        final EditText et_yas=(EditText)findViewById(R.id.et_yas);
        final EditText et_kullaniciadi=(EditText)findViewById(R.id.et_kullanici);
        final EditText et_sifre=(EditText)findViewById(R.id.et_sifre);


        final Spinner sp_cinsiyet=(Spinner)findViewById(R.id.sp_cinsiyet);
        final Spinner sp_adres=(Spinner)findViewById(R.id.sp_adres);

        final TextView tv_sonuc=(TextView)findViewById(R.id.tv_sonuc);

        Button btn_gonder=(Button)findViewById(R.id.btn_gonder);

        final Veritabani db= new Veritabani(KayitActivity.this);

        btn_gonder.setOnClickListener(new View.OnClickListener() {//kayıt işlemi gerçekleştirildi
            @Override
            public void onClick(View view) {

                String ad=et_ad.getText().toString();
                String soyad=et_soyad.getText().toString();
                String telefon=et_telefon.getText().toString();
                String yas=et_yas.getText().toString();
                String kullaniciadi=et_kullaniciadi.getText().toString();
                String sifre=et_sifre.getText().toString();


                String cinsiyet=sp_cinsiyet.getSelectedItem().toString();
                String adres=sp_adres.getSelectedItem().toString();




                boolean sonuc=  db.KayitOl(ad,soyad,yas,cinsiyet,adres,telefon,kullaniciadi,sifre);

                if(sonuc==true )  tv_sonuc.setText("eklendi");
                else
                    tv_sonuc.setText("eklenmedi");

                Intent geridon=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(geridon);

            }
        });





    }
}
