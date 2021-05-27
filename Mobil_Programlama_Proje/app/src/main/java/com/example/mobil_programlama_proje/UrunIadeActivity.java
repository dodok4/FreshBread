package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UrunIadeActivity extends AppCompatActivity {
String kullaniciadii;
int siparisid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_iade);


        final EditText et_iade=(EditText)findViewById(R.id.et_iade);
        Button btn_iadeet =(Button) findViewById(R.id.btn_iadeet);
        Button btn_iadeguncelle =(Button) findViewById(R.id.btn_iadeguncelle);
        final ListView liste_iadelistesi = (ListView) findViewById(R.id.liste_iadelistesi);
        final TextView tv_durum=(TextView)findViewById(R.id.tv_durum);

        final Veritabani db= new Veritabani(UrunIadeActivity.this);


        final ArrayList<String> urunadettut = new ArrayList<>();
        final ArrayList<String> urunidtut = new ArrayList<>();
        final ArrayList<String> urunaditut = new ArrayList<>();
        final ArrayList<String> bakiyetut = new ArrayList<>();
        final ArrayList<String> harcaanantut = new ArrayList<>();
        final ArrayList<String> sipdurumtut = new ArrayList<>();
        final ArrayList<String> kayit2 = new ArrayList<>();
        final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kayit2);

        final ArrayList<String> siparisadeditut = new ArrayList<>();
        final ArrayList<String> siparistutarivtut = new ArrayList<>();

        Intent intent=getIntent();
        kullaniciadii=intent.getStringExtra("kullan");

        Cursor q=db.SiparisListele(kullaniciadii);

        if (q.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Herhangi bir sipariş kaydı yoktur", Toast.LENGTH_SHORT).show();

        } else {
            ((ArrayAdapter) listAdapter).clear();
            while (q.moveToNext()) {
                kayit2.add(q.getString(0) + "--" + q.getString(1) + "--" + q.getString(2) + "--" + q.getString(3)+"--"+ q.getString(4)
                );

            }
            liste_iadelistesi.setAdapter(listAdapter);
        }


        liste_iadelistesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gelenveri=liste_iadelistesi.getItemAtPosition(position).toString();

                String[] veri=gelenveri.split("--");
                et_iade.setText(veri[0]);



            }
        });


        btn_iadeguncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor q=db.SiparisListele(kullaniciadii);

                if (q.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Herhangi bir sipariş kaydı yoktur", Toast.LENGTH_SHORT).show();

                } else {
                    ((ArrayAdapter) listAdapter).clear();
                    while (q.moveToNext()) {
                        kayit2.add(q.getString(0) + "--" + q.getString(1) + "--" + q.getString(2) + "--" + q.getString(3)+"--"+ q.getString(4)
                        );

                    }
                    liste_iadelistesi.setAdapter(listAdapter);
                }

            }
        });

        btn_iadeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 siparisid=Integer.valueOf(et_iade.getText().toString());



                Cursor z=db.SiparisDurumu(siparisid);
                sipdurumtut.clear();

                z.moveToNext();
                sipdurumtut.add(z.getString(0));

                String FalseorTrue=String.valueOf(sipdurumtut.get(0));



                if(FalseorTrue.equals("FALSE")){


                    Cursor n=db.SecilenSiparisVeriAl(siparisid);
                    siparisadeditut.clear();//ürün adedi ve tutarı bulundu
                    n.moveToNext();
                    siparisadeditut.add(n.getString(0));//ürün adedi ve tutarı bulundu


                    Cursor m=db.SecilenSiparisVeriAl2(siparisid);
                    siparistutarivtut.clear();//ürün adedi ve tutarı bulundu
                    m.moveToNext();
                    siparistutarivtut.add(m.getString(0));



                    int adet=Integer.valueOf(siparisadeditut.get(0));
                    double siparistutari=Double.valueOf(siparistutarivtut.get(0));
                    double mevcuttoplamHarcanan;
                    double harcanangucelle;

                    Cursor r=db.ToplamHarcanan(kullaniciadii);
                    harcaanantut.clear();
                    r.moveToNext();
                    harcaanantut.add(r.getString(0));


                    mevcuttoplamHarcanan=Double.valueOf(harcaanantut.get(0));
                    harcanangucelle=mevcuttoplamHarcanan-siparistutari;
                    db.ToplamHarcananGuncelle(kullaniciadii,harcanangucelle);


                    Cursor t=db.BakiyeListele(kullaniciadii);
                    bakiyetut.clear();
                    t.moveToNext();
                    bakiyetut.add(t.getString(0));

                    double mevcutbakiye=Double.valueOf(bakiyetut.get(0));
                    double gonderilecekbakiye=mevcutbakiye+siparistutari;
                    db.BakiyeGuncelle(kullaniciadii,gonderilecekbakiye);



                    Cursor u=db.SiparisdenUrunadina(kullaniciadii,siparisid);
                    urunaditut.clear();
                    u.moveToNext();
                    urunaditut.add(u.getString(0));





                     Cursor tu=db.urunidbul(urunaditut.get(0));
                    urunidtut.clear();
                    tu.moveToFirst();
                    urunidtut.add(tu.getString(0));


                    int productid=Integer.valueOf(urunidtut.get(0));

                    int mevcuturunadedi;

                    Cursor t7=db.kalanurunadetbul(urunaditut.get(0));
                    urunadettut.clear();
                    t7.moveToNext();
                    urunadettut.add(t7.getString(0));

                    mevcuturunadedi=Integer.valueOf(urunadettut.get(0));
                    int gonderilecekurunadedi=mevcuturunadedi+adet;

                    db.Urunadediguncelle(gonderilecekurunadedi,productid);


                    db.SiparisIadeEt(siparisid);


                    Toast.makeText(getApplicationContext(),"Siparişiniz iade edilmiştir",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Sipariş tutarı hesabınıza geçmiştir",Toast.LENGTH_LONG).show();


                }
                else {

                    Toast.makeText(getApplicationContext(),"İade ettiğiniz bir ürünü tekrar iade edemezsiniz",Toast.LENGTH_LONG).show();
                }




            }
        });


    }
}
