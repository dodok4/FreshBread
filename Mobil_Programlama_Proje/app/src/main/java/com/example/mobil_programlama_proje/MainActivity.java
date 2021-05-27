package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn_giris=(Button)findViewById(R.id.btn_giris);
        final Button btn_kayit=(Button)findViewById(R.id.btn_kayit);

        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sayfa_gec =new Intent(getApplicationContext(),KayitActivity.class);
                startActivity(sayfa_gec);

            }
        });//Kayıt butonuna tıklandığında kayıt sayfasına geçiş yapılıyor.

        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giris_secim=new Intent(getApplicationContext(),GirisSecimActivity.class);
                startActivity(giris_secim);
            }
        });




    }
}
