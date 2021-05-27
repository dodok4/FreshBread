package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BakiyeYuklemeEkraniActivity extends AppCompatActivity {
    String kad="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakiye_yukleme_ekrani);

        final EditText et_yuklenecekmiktar=(EditText)findViewById(R.id.et_yuklenecekmiktar);
        Button btn_yukle=(Button)findViewById(R.id.btn_yukle);
        Button btn_bakiyeguncelle=(Button)findViewById(R.id.btn_bakiyeguncelle);

        final ArrayList<String> bakiye = new ArrayList<>();
        final ArrayList<String> idtut = new ArrayList<>();
        final TextView tv_mevcutbakiyetutari=(TextView)findViewById(R.id.tv_mevcutbakiyetutari);


        final Veritabani db= new Veritabani(BakiyeYuklemeEkraniActivity.this);


        Intent intent=getIntent();
        kad=intent.getStringExtra("kullan");


        btn_yukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                kad=intent.getStringExtra("kullan");


                double ymiktar=Double.parseDouble(et_yuklenecekmiktar.getText().toString());

                Cursor dat = db.idbul(kad);
                idtut.clear();

                    dat.moveToFirst();
                    idtut.add(dat.getString(0));


                Cursor data = db.BakiyeListele(kad);
                bakiye.clear();

                    data.moveToFirst();
                    bakiye.add(data.getString(0));

                double mevcut=Double.parseDouble(bakiye.get(0));
                double gonder=mevcut+ymiktar;



                db.BakiyeGuncelle(kad,gonder);

                Toast.makeText(getApplicationContext(),"Bakiyeniz yükleme işleminiz gerçekleştirilmiştir.",Toast.LENGTH_LONG).show();


            }
        });

       btn_bakiyeguncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Cursor data = db.BakiyeListele(kad);
                bakiye.clear();

                    data.moveToFirst();
                    bakiye.add(data.getString(0));



                tv_mevcutbakiyetutari.setText("Mevcut Bakiye:"+bakiye.get(0).toString());

            }
        });

    }
}
