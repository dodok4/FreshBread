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

public class BakiyeTransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakiye_transfer);

        final EditText et_yuklenecekmiktar=(EditText)findViewById(R.id.et_transfertutar);
        Button btn_transfer=(Button)findViewById(R.id.btn_transfer);
        Button btn_mevcutb=(Button)findViewById(R.id.btn_mevcutb);
        final TextView tv_bakiyemevcut=(TextView)findViewById(R.id.tv_bakiyemevcut);

        final Veritabani db= new Veritabani(BakiyeTransferActivity.this);
        final ArrayList<String> bakiye = new ArrayList<>();

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kad;
                Intent intent=getIntent();
                kad=intent.getStringExtra("kullan");


                double ymiktar=Double.parseDouble(et_yuklenecekmiktar.getText().toString());


                Cursor data = db.BakiyeListele(kad);
                bakiye.clear();

                data.moveToFirst();
                bakiye.add(data.getString(0));

                double mevcut=Double.parseDouble(bakiye.get(0));
                double gonder=mevcut-ymiktar;



                db.BakiyeTransfer(kad,gonder);

                Toast.makeText(getApplicationContext(),"Bakiyeniz transfer edilmiştir.",Toast.LENGTH_LONG).show();



            }
        });
        btn_mevcutb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kad;
                Intent intent=getIntent();
                kad=intent.getStringExtra("kullan");

                Cursor data = db.BakiyeListele(kad);
                bakiye.clear();

                data.moveToFirst();
                bakiye.add(data.getString(0));


                tv_bakiyemevcut.setText("Mevcut Bakiye:"+bakiye.get(0).toString());




            }
        });



    }
}
