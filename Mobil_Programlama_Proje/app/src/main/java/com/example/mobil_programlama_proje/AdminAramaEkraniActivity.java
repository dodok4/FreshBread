package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminAramaEkraniActivity extends AppCompatActivity {
    RadioButton rb;
    String kriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_arama_ekrani);



       final Spinner sp_arama =(Spinner)findViewById(R.id.sp_arama);
       final RadioGroup rg_grup=(RadioGroup)findViewById(R.id.rg_grup);

       final RadioButton rb_ad=(RadioButton)findViewById(R.id.rb_ad);
       final RadioButton rb_soyad=(RadioButton)findViewById(R.id.rb_soyad);
       final RadioButton rb_adres=(RadioButton)findViewById(R.id.rb_adres);

       final EditText et_alternatifarama =(EditText) findViewById(R.id.et_alternatifarama);
       Button btn_arama =(Button) findViewById(R.id.btn_arama);
       Button btn_alternatif =(Button) findViewById(R.id.btn_alternatif);
        final Veritabani db= new Veritabani(AdminAramaEkraniActivity.this);
        final ArrayList<String> kayit3 = new ArrayList<>();
        final ArrayList<String> kayitkontrol = new ArrayList<>();
        final ListAdapter listAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kayit3);

        final ListView liste_aramalistele=(ListView)findViewById(R.id.liste_aramalistele);




        sp_arama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                kriter=sp_arama.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    btn_arama.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (kriter.equals("Erkek Müşteriler")){
                Cursor qa = db.ErkekMusteriler();

                if (qa.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Erkek müşterimiz bulunmamamaktadır", Toast.LENGTH_SHORT).show();

                }
                else {

                    ((ArrayAdapter) listAdapter3).clear();
                    while (qa.moveToNext()) {

                        kayit3.add(qa.getString(0) + "--" + qa.getString(1) + "--" + qa.getString(2) + "--" + qa.getString(3) + "--" + qa.getString(4) + "--" + qa.getString(5)
                                + "--" + qa.getString(6) );




                    }
                    liste_aramalistele.setAdapter(listAdapter3);
                }

            }
            else if(kriter.equals("Kadın Müşteriler")){
                Cursor qb = db.KadinMusteriler();

                if (qb.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Kadın müşterimiz bulunmamamaktadır", Toast.LENGTH_SHORT).show();

                }
                else {

                    ((ArrayAdapter) listAdapter3).clear();
                    while (qb.moveToNext()) {

                        kayit3.add(qb.getString(0) + "--" + qb.getString(1) + "--" + qb.getString(2) + "--" + qb.getString(3) + "--" + qb.getString(4) + "--" + qb.getString(5)
                                + "--" + qb.getString(6) );




                    }
                    liste_aramalistele.setAdapter(listAdapter3);
                }


            }
            else if(kriter.equals("En Yüksek Sipariş Tutarı")){
                Cursor qc = db.EnyuksekSiparisTutari();

                if (qc.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "herhangi bir müşteri kaydı yok", Toast.LENGTH_SHORT).show();

                }
                else {

                    ((ArrayAdapter) listAdapter3).clear();
                    while (qc.moveToNext()) {
                        kayit3.add(qc.getString(0) + "--" + qc.getString(1) + "--" + qc.getString(2) + "--" + qc.getString(3) + "--" + qc.getString(4));
                    }
                    liste_aramalistele.setAdapter(listAdapter3);
                }


            }
            else if(kriter.equals("İade Edilen Siparişler")){
                Cursor qb = db.araiadelistesi();

                if (qb.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "İade edilen herhangi bir ürün bulunmamaktadır", Toast.LENGTH_SHORT).show();

                }
                else {

                    ((ArrayAdapter) listAdapter3).clear();
                    while (qb.moveToNext()) {

                        kayit3.add(qb.getString(0) + "--" + qb.getString(1) + "--" + qb.getString(2) + "--" + qb.getString(3) + "--" + qb.getString(4) + "--" + qb.getString(5)
                                 );




                    }
                    liste_aramalistele.setAdapter(listAdapter3);
                }


            }
            else if(kriter.equals("En Çok Harcama Yapan Müşteri")){
                Cursor qb = db.EnCokHarcamaYapanMusteri();

                if (qb.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Herhangibir Müşteri Kaydı Yok", Toast.LENGTH_SHORT).show();

                }
                else {

                    ((ArrayAdapter) listAdapter3).clear();
                    while (qb.moveToNext()) {

                        kayit3.add(qb.getString(0) + "--" + qb.getString(1) + "--" + qb.getString(2)
                        );




                    }
                    liste_aramalistele.setAdapter(listAdapter3);
                }


            }
            else if(kriter.equals("En Az Harcama Yapan Müşteri")){
                Cursor qb = db.EnAzHarcamaYapanMusteri();

                if (qb.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Herhangibir Müşteri Kaydı Yok", Toast.LENGTH_SHORT).show();

                }
                else {

                    ((ArrayAdapter) listAdapter3).clear();
                    while (qb.moveToNext()) {

                        kayit3.add(qb.getString(0) + "--" + qb.getString(1) + "--" + qb.getString(2)
                        );




                    }
                    liste_aramalistele.setAdapter(listAdapter3);
                }


            }
            else if(kriter.equals("Şirket Hesabı")){
                Cursor qb = db.SirketBakiyesi();

                if (qb.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Herhangibir hesap bakiyesi bulunamadı ", Toast.LENGTH_SHORT).show();

                }
                else {

                    ((ArrayAdapter) listAdapter3).clear();
                    while (qb.moveToNext()) {

                        kayit3.add(qb.getString(0)
                        );




                    }
                    liste_aramalistele.setAdapter(listAdapter3);
                }


            }



        else {Toast.makeText(getApplicationContext(),"Lütfen bir seçim kriteri belirleyiniz",Toast.LENGTH_LONG).show();}
        }
    });



    btn_alternatif.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        int butonid = rg_grup.getCheckedRadioButtonId();

        if (!"".equals(et_alternatifarama.getText().toString())){


            switch (butonid) {
                case R.id.rb_ad:


                    Cursor q = db.AdaGoreArama(et_alternatifarama.getText().toString());

                    if (q.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Aradığınız ad da herhangi bir müşteri yok", Toast.LENGTH_SHORT).show();

                    }
                    else {


                        ((ArrayAdapter) listAdapter3).clear();

                        while (q.moveToNext()) {

                            kayit3.add(q.getString(0) + "--" + q.getString(1) + "--" + q.getString(2) + "--" + q.getString(3) + "--" + q.getString(4) + "--" + q.getString(5)
                                    + "--" + q.getString(6) );




                        }
                        liste_aramalistele.setAdapter(listAdapter3);
                    }

                    break;


                case R.id.rb_soyad:
                    Cursor m = db.SoyadaGoreArama((et_alternatifarama.getText().toString()));

                    if (m.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Aradığınız soyad da herhangi bir müşteri yok", Toast.LENGTH_SHORT).show();

                    }
                    else {


                        ((ArrayAdapter) listAdapter3).clear();

                        while (m.moveToNext()) {





                            kayit3.add(m.getString(0) + "--" + m.getString(1) + "--" + m.getString(2) + "--" + m.getString(3) + "--" + m.getString(4) + "--" + m.getString(5)
                                    + "--" + m.getString(6) );




                        }
                        liste_aramalistele.setAdapter(listAdapter3);
                    }
                    Toast.makeText(getApplicationContext(),"Listeuzunlugu="+kayit3.size(),Toast.LENGTH_LONG).show();

                    break;


                case R.id.rb_adres:
                    Cursor z = db.AdreseGoreArama(et_alternatifarama.getText().toString());

                    if (z.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Aradığınız adrese ait herhangi bir müşteri yok", Toast.LENGTH_SHORT).show();

                    }
                    else {


                        ((ArrayAdapter) listAdapter3).clear();

                        while (z.moveToNext()) {

                            kayit3.add(z.getString(0) + "--" + z.getString(1) + "--" + z.getString(2) + "--" + z.getString(3) + "--" + z.getString(4) + "--" + z.getString(5)
                                    + "--" + z.getString(6) );




                        }
                        liste_aramalistele.setAdapter(listAdapter3);
                    }



                    break;


            }

    }
    else
    Toast.makeText(getApplicationContext(),"Lütfen bir arama kriteri seçin",Toast.LENGTH_LONG).show();
    }
});
    }

}
