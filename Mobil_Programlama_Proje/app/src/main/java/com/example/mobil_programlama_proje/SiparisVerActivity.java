package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SiparisVerActivity extends AppCompatActivity {
    String siparisverkullaniciadi;
    String veri;
    int uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_ver);

        final Spinner sp_urun =(Spinner)findViewById(R.id.sp_urun);
        final  TextView tv_kalanurunadedi =(TextView)findViewById(R.id.tv_kalanurunadedi);
        final  TextView tv_urunfiyati =(TextView)findViewById(R.id.tv_urunfiyati);
        final  TextView tv_bakiye =(TextView)findViewById(R.id.tv_bakiye);
        final EditText et_urunadedi =(EditText) findViewById(R.id.et_urunadedi);
        Button btn_siparisver =(Button) findViewById(R.id.btn_siparisver);



        final Veritabani db= new Veritabani(SiparisVerActivity.this);

        ArrayList<String> alinan_listele=db.UrunleriGetir();

        final ArrayList<String> kalanuruntut= new ArrayList<>();
        final ArrayList<String> fiyattut= new ArrayList<>();
        final ArrayList<String> idtut= new ArrayList<>();
        final ArrayList<String> urunidtut= new ArrayList<>();
        final ArrayList<String> bakiyetut= new ArrayList<>();
        final ArrayList<String> tharcanantut= new ArrayList<>();

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,alinan_listele);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_urun.setAdapter(adapter);








        sp_urun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               veri=sp_urun.getSelectedItem().toString();


                Cursor data = db.kalanurunadetbul(veri);//kalan ürün adedi bulunuyor
                kalanuruntut.clear();
                while (data.moveToNext())
                kalanuruntut.add(data.getString(0));

                Cursor x= db.urunidbul(veri);// ürün id bulunuyor
                urunidtut.clear();

                x.moveToNext();
                urunidtut.add(x.getString(0));

                uid=Integer.valueOf(urunidtut.get(0).toString());

                Cursor dat= db.fiyatbul(veri);// ürün fiyat bulunuyor
                fiyattut.clear();
                while (dat.moveToNext())
                    fiyattut.add(dat.getString(0));

                tv_urunfiyati.setText("Ürünün Fiyatı="+fiyattut.get(0));
                tv_kalanurunadedi.setText("Kalanurunadet="+kalanuruntut.get(0));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_siparisver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int adet=Integer.valueOf(et_urunadedi.getText().toString());



                Intent intent=getIntent();
                siparisverkullaniciadi=intent.getStringExtra("kullan");

                Cursor d = db.idbul(siparisverkullaniciadi);//kullanici id bulundu
                idtut.clear();

                d.moveToNext();
                idtut.add(d.getString(0));





                int urunid=uid;
                int musteriid=Integer.valueOf(idtut.get(0));
                double fiyat=Double.parseDouble(fiyattut.get(0));
                int yenistok;
                double sonuc=fiyat*adet;//sipariş tutarı



                Cursor dc= db.BakiyeListele(siparisverkullaniciadi);// bakiye bulunuyor
                bakiyetut.clear();

                dc.moveToFirst();
                bakiyetut.add(dc.getString(0));

                double bak=Double.valueOf(bakiyetut.get(0));

                if(sonuc<=bak){

                    boolean karar=  db.Siparis_Ekleme(musteriid,adet,sonuc,urunid);

                    if(karar==true){

                        Toast.makeText(getApplicationContext(), "sipariş işleminiz başarılı", Toast.LENGTH_LONG).show();
                        yenistok = Integer.parseInt(kalanuruntut.get(0)) - adet;

                        Cursor q = db.ToplamHarcanan(siparisverkullaniciadi);//kalan ürün adedi bulunuyor
                        tharcanantut.clear();
                        while (q.moveToNext())
                            tharcanantut.add(q.getString(0));

                        double bugunekadarharcanan=Double.valueOf(tharcanantut.get(0));

                        double harcananguncelle=sonuc+bugunekadarharcanan;
                        db.ToplamHarcananGuncelle(siparisverkullaniciadi,harcananguncelle);

                        db.Urunadediguncelle(yenistok, Integer.valueOf(urunidtut.get(0)));


                        double bakiyekontrol=Double.valueOf(bakiyetut.get(0).toString());
                        double bakiyeyenile=bakiyekontrol-sonuc;

                        db.BakiyeGuncelle(siparisverkullaniciadi,bakiyeyenile);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"sipariş verme işleminiz başarısız",Toast.LENGTH_LONG).show();
                    }


                }

                else{

                    Toast.makeText(getApplicationContext(), "bakiyeniz yeterli değil", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "bakiyeniz:"+bakiyetut.get(0), Toast.LENGTH_SHORT).show();
                    }


                    }





        });



    }
}
