package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminUrunGuncelle extends AppCompatActivity {

    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_urun_guncelle);

        Button btn_gorevguncelle =(Button)findViewById(R.id.btn_gorevguncelle);
        Button btn_gecicisil=(Button)findViewById(R.id.btn_gecicisil);
        Button btn_listele=(Button)findViewById(R.id.btn_listele);

        final EditText et_gelenid=(EditText)findViewById(R.id.et_gelenid);
        final EditText et_gelenurunadi=(EditText)findViewById(R.id.et_gelenurunadi);
        final EditText et_gelenurunadet=(EditText)findViewById(R.id.et_gelenurunadet);
        final EditText et_gelenurunfiyat=(EditText)findViewById(R.id.et_gelenurunfiyat);


        final Veritabani db= new Veritabani(AdminUrunGuncelle.this);


        final ListView listele = (ListView) findViewById(R.id.list_listele1);
        final ArrayList<String> kayit = new ArrayList<>();
        final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kayit);




            Cursor data = db.UrunListele();

            if (data.getCount() == 0) {
                Toast.makeText(AdminUrunGuncelle.this, "Veri yok!", Toast.LENGTH_SHORT).show();

            } else {
                ((ArrayAdapter) listAdapter).clear();
                while (data.moveToNext()) {
                    kayit.add(data.getString(0) + "--" + data.getString(1) + "--" + data.getString(2) + "--" + data.getString(3)
                    );

                }
                listele.setAdapter(listAdapter);
            }





        listele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String alinan_veri = listele.getItemAtPosition(i).toString();


                String[] veridizi=alinan_veri.split("--");
                id=Integer.valueOf(veridizi[0]);
                et_gelenid.setText(veridizi[0]);
                et_gelenurunadi.setText(veridizi[1].toString());
                et_gelenurunadet.setText(veridizi[2].toString());
                et_gelenurunfiyat.setText(veridizi[3].toString());

            }
        });

       btn_gorevguncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        String gelenurunadi = et_gelenurunadi.getText().toString();
                        int gelenurunadedi = Integer.parseInt(et_gelenurunadet.getText().toString());
                        double gelenurunfiyati  = Double.valueOf(et_gelenurunfiyat.getText().toString());

                        Veritabani vt= new Veritabani(AdminUrunGuncelle.this);
                        vt.Urun_Guncelle(id,gelenurunadi,gelenurunadedi,gelenurunfiyati);

                    }


        });

       btn_listele.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Cursor data = db.UrunListele();

               if (data.getCount() == 0) {
                   Toast.makeText(AdminUrunGuncelle.this, "Veri yok!", Toast.LENGTH_SHORT).show();

               } else {
                   ((ArrayAdapter) listAdapter).clear();
                   while (data.moveToNext()) {
                       kayit.add(data.getString(0) +"--" +data.getString(1) + "--" + data.getString(2) + "--" + data.getString(3)
                       );

                   }
                   listele.setAdapter(listAdapter);
               }

           }
       });


       btn_gecicisil.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Veritabani vt= new Veritabani(AdminUrunGuncelle.this);

               vt.VeriSil(id);




           }
       });

    }
}
