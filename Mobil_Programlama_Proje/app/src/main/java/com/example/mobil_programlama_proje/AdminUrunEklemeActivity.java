package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminUrunEklemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_urun_ekleme);

        Button btn_urunekle=(Button)findViewById(R.id.btn_urunekle);



        final EditText et_eklenecekurunadi=(EditText)findViewById(R.id.et_eklenecekurunadi);
        final EditText et_eklenecekurunadedi=(EditText)findViewById(R.id.et_eklenecekurunadedi);
        final EditText et_eklenecekurunfiyati=(EditText)findViewById(R.id.et_eklenecekurunfiyati);

        final Veritabani db= new Veritabani(AdminUrunEklemeActivity.this);
        btn_urunekle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String eklenecekurunadi=et_eklenecekurunadi.getText().toString();
            int eklenecekurunadet=Integer.valueOf(et_eklenecekurunadedi.getText().toString());
           double eklenecekurunfiyat=Double.valueOf(et_eklenecekurunfiyati.getText().toString());

            boolean sonuc=db.Urun_Ekleme(eklenecekurunadi,eklenecekurunadet,eklenecekurunfiyat);

            if(sonuc==true )
                Toast.makeText(AdminUrunEklemeActivity.this, "urun eklendi", Toast.LENGTH_SHORT).show();

            else
                Toast.makeText(AdminUrunEklemeActivity.this, "urun eklenmedi", Toast.LENGTH_SHORT).show();



        }
    });
    }






}
