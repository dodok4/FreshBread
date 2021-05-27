package com.example.mobil_programlama_proje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Veritabani extends SQLiteOpenHelper {


    private static final String Veritabani_Adi ="Market_Sistemi";
    private static final int versiyon=5;

    private static final String tablo_adi ="musteri";
    private static final String tablo_adi1 ="urunbilgisi";
    private static final String tablo_adi2 ="siparisbilgisi";

    private static final String ID_alani ="ID";
    private static final String Ad_alani ="ad";
    private static final String Soyad_alani ="soyad";
    private static final String Yas_alani = "yas";
    private static final String Cinsiyet_alani = "cinsiyet";
    private static final String Adres_alani = "adres";
    private static final String Telefon_alani = "numara";
    private static final String Bakiye_alani = "bakiye";
    private static final String ToplamHarcananTutar_alani = "toplamtutar";
    private static final String Kullanici_alani = "kullaniciadi";
    private static final String Sifre_alani = "sifre";

    private static final String UrunID_alani="urunid";
    private static final String UrunAd_alani="urunadi";
    private static final String UrunAlimAdeti_alani="urunadet";
    private static final String UrunFiyat_alani="urunfiyat";


    private static final String SiparisID_alani="siparisid";
    private static final String SiparisAdedi_alani="urunstok";
    private static final String SiparisTutari_alani="tutar";
    private static final String SiparisIade_alani="iade";



    public Veritabani(@Nullable Context context) {

        super(context, Veritabani_Adi,null, versiyon);

    }





    @Override
    public void onCreate(SQLiteDatabase db) {

        String tablo_olustur="Create table "+tablo_adi+" (ID INTEGER PRIMARY KEY AUTOINCREMENT , ad TEXT, soyad TEXT, yas TEXT,cinsiyet TEXT,adres TEXT,numara TEXT,bakiye REAL,toplamtutar REAL,kullaniciadi TEXT UNIQUE,sifre TEXT)";
        String urun_olustur ="Create table "+tablo_adi1+"(urunid INTEGER PRIMARY KEY AUTOINCREMENT ,urunadi TEXT UNIQUE,urunadet INTEGER,urunfiyat REAL)";

        String siparis_olustur="Create table "+tablo_adi2+"(siparisid INTEGER PRIMARY KEY AUTOINCREMENT,ID INTEGER,urunstok INTEGER,tutar REAL,urunid Integer,iade TEXT DEFAULT 'FALSE',foreign key(ID) REFERENCES "+tablo_adi+" (ID),Foreign key(urunid) REFERENCES "+tablo_adi1+" (urunid))";

        db.execSQL(tablo_olustur);
        db.execSQL(urun_olustur);
        db.execSQL(siparis_olustur);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tablo_adi);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tablo_adi1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tablo_adi2);
        onCreate(sqLiteDatabase);


    }

    public boolean KayitOl(String Ad, String Soyad, String Yas,String Cinsiyet,String Adres,String Telefon,String kullaniciadi,String sifre)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Ad_alani,Ad);
        cv.put(Soyad_alani,Soyad);
        cv.put(Yas_alani,Yas);
        cv.put(Cinsiyet_alani,Cinsiyet);
        cv.put(Adres_alani,Adres);
        cv.put(Telefon_alani,Telefon);
        cv.put(Bakiye_alani,0);
        cv.put(ToplamHarcananTutar_alani,0);
        cv.put(Kullanici_alani,kullaniciadi);
        cv.put(Sifre_alani,sifre);


        long result=db.insert(tablo_adi,null,cv);


        if(result==-1){

            return false;
        }
        return true;

    }





   public void Urun_Guncelle(int id,String urunadi,int urunadet,double urunfiyat){

       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues cv=new ContentValues();
       cv.put(UrunAd_alani,urunadi);
       cv.put(UrunAlimAdeti_alani,urunadet);
       cv.put(UrunFiyat_alani,urunfiyat);
       cv.put(UrunID_alani,id);
       String sorgu=UrunID_alani +" = " + id;

       db.update(tablo_adi1,cv,sorgu,null);
       db.close();


   }

    public Cursor AdaGoreArama(String ad) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor n =db.rawQuery("SELECT ad,soyad,cinsiyet,adres,numara,bakiye,toplamtutar FROM "+tablo_adi+" WHERE ad LIKE ?",new String[] {"%"+ad+"%"});
        return n;
    }
    public Cursor SoyadaGoreArama(String soyad) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor n =db.rawQuery("SELECT ad,soyad,cinsiyet,adres,numara,bakiye,toplamtutar FROM "+tablo_adi+" WHERE soyad LIKE ?",new String[] {"%"+soyad+"%"});
        return n;
    }
    public Cursor AdreseGoreArama(String adres) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor n =db.rawQuery("SELECT ad,soyad,cinsiyet,adres,numara,bakiye,toplamtutar FROM "+tablo_adi+" WHERE adres LIKE ?",new String[] {"%"+adres+"%"});
        return n;
    }

    public Cursor ErkekMusteriler(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor emusteri =db.rawQuery("SELECT ad,soyad,cinsiyet,adres,numara,bakiye,toplamtutar FROM "+tablo_adi+" WHERE cinsiyet=?",new String[]{"Erkek"});
        return emusteri;

    }
    public Cursor KadinMusteriler(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor emusteri =db.rawQuery("SELECT ad,soyad,cinsiyet,adres,numara,bakiye,toplamtutar FROM "+tablo_adi+" WHERE cinsiyet=?",new String[]{"Kadın"});
        return emusteri;
    }
    public Cursor EnyuksekSiparisTutari(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor est =db.rawQuery("SELECT siparisid,ad,soyad,urunadi,tutar FROM "+tablo_adi+" JOIN "+tablo_adi2+" ON "+tablo_adi+".ID"+" = "+tablo_adi2+".ID JOIN "+tablo_adi1+" ON "+tablo_adi1+".urunid"+"="+tablo_adi2+".urunid "+" ORDER BY  tutar DESC LIMIT 1",null);
        return est;
    }
    public Cursor araiadelistesi(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor sipurlist =db.rawQuery("SELECT siparisid,ad,soyad,urunadi,urunstok,iade FROM "+tablo_adi+" JOIN "+tablo_adi2+" ON "+tablo_adi+".ID"+" = "+tablo_adi2+".ID JOIN "+tablo_adi1+" ON "+tablo_adi1+".urunid"+"="+tablo_adi2+".urunid WHERE iade=?",new String[]{"TRUE"});
        return sipurlist;
    }
    public Cursor EnCokHarcamaYapanMusteri(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ysipurlist =db.rawQuery("SELECT ad,soyad,toplamtutar FROM "+tablo_adi+" JOIN "+tablo_adi2+" ON "+tablo_adi+".ID"+" = "+tablo_adi2+".ID JOIN "+tablo_adi1+" ON "+tablo_adi1+".urunid"+"="+tablo_adi2+".urunid ORDER BY toplamtutar DESC LIMIT 1",null);
        return ysipurlist;
    }
    public Cursor EnAzHarcamaYapanMusteri(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor asipurlist =db.rawQuery("SELECT ad,soyad,toplamtutar FROM "+tablo_adi+" JOIN "+tablo_adi2+" ON "+tablo_adi+".ID"+" = "+tablo_adi2+".ID JOIN "+tablo_adi1+" ON "+tablo_adi1+".urunid"+"="+tablo_adi2+".urunid ORDER BY toplamtutar ASC LIMIT 1",null);
        return asipurlist;
    }
    public Cursor SirketBakiyesi(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor asipurlist =db.rawQuery("SELECT SUM(toplamtutar) FROM "+tablo_adi,null);
        return asipurlist;
    }

    public Cursor girisSorgula(String kullanicii,String sifree){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor veri=db.rawQuery("SELECT kullaniciadi FROM "+tablo_adi+" WHERE kullaniciadi=?  AND sifre=?", new String[] { kullanicii ,sifree});
        //Cursor veri=db.rawQuery("SELECT kullaniciadi FROM "+tablo_adi+" WHERE kullaniciadi='"+kullanicii+" 'AND sifre='"+sifree+"'",null);
        return veri;

    }
    public Cursor kalanurunadetbul(String urununadi){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor bul=db.rawQuery("SELECT urunadet FROM "+tablo_adi1+" WHERE urunadi=?",new String[]{urununadi});
        return  bul;

    }

    public Cursor ToplamHarcanan(String kullaniciadi)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor ara=db.rawQuery("SELECT toplamtutar FROM "+tablo_adi+" WHERE kullaniciadi=?",new String[]{kullaniciadi});
        return  ara;



    }

    public Cursor urunidbul(String urununadi){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor bul=db.rawQuery("SELECT urunid FROM "+tablo_adi1+" WHERE urunadi=?",new String[]{urununadi});
        return  bul;

    }


    public Cursor fiyatbul(String urununadi){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor bul=db.rawQuery("SELECT urunfiyat FROM "+tablo_adi1+" WHERE urunadi=?",new String[]{urununadi});
        return  bul;

    }




    public Cursor idbul(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor bul=db.rawQuery("SELECT ID FROM "+tablo_adi+" WHERE kullaniciadi=?",new String[]{username});
        return  bul;

    }




    public void Urunadediguncelle(int adet,int urunid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(UrunAlimAdeti_alani,adet);
        db.execSQL("UPDATE " +tablo_adi1+" SET urunadet="+String.valueOf(adet)+" WHERE urunid='"+urunid+"'");


        db.close();
    }

    public void ToplamHarcananGuncelle(String kullaniciadi, double tutar)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ToplamHarcananTutar_alani,tutar);
        db.execSQL("UPDATE " +tablo_adi+" SET toplamtutar= "+String.valueOf(tutar)+" WHERE kullaniciadi='"+kullaniciadi+"'");


        db.close();
    }





    public void BakiyeTransfer(String kullaniciadi, double tutar)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Bakiye_alani,tutar);
        db.execSQL("UPDATE " +tablo_adi+" SET bakiye= "+String.valueOf(tutar)+" WHERE kullaniciadi='"+kullaniciadi+"'");


        db.close();
    }


    public void BakiyeGuncelle(String kullaniciadi, double tutar)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Bakiye_alani,tutar);
        db.execSQL("UPDATE " +tablo_adi+" SET bakiye= "+String.valueOf(tutar)+" WHERE kullaniciadi='"+kullaniciadi+"'");



        db.close();
    }



    public Cursor BakiyeListele(String Username){

        SQLiteDatabase db=this.getWritableDatabase();
            Cursor veri=db.rawQuery("SELECT bakiye FROM "+tablo_adi+" WHERE kullaniciadi=?", new String[] { Username });

            return veri;
    }


    public Cursor SiparisdenUrunadina(String Username,int siparisid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor n =db.rawQuery("SELECT urunadi FROM "+tablo_adi+" JOIN "+tablo_adi2+" ON "+tablo_adi+".ID"+" = "+tablo_adi2+".ID JOIN "+tablo_adi1+" ON "+tablo_adi1+".urunid"+"="+tablo_adi2+".urunid WHERE kullaniciadi=? AND siparisid=?",new String[] { Username, String.valueOf(siparisid)});
        return n;
    }


    public Cursor SiparisListele(String Username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor n =db.rawQuery("SELECT siparisid,urunadi,urunstok,tutar,iade FROM "+tablo_adi+" JOIN "+tablo_adi2+" ON "+tablo_adi+".ID"+" = "+tablo_adi2+".ID JOIN "+tablo_adi1+" ON "+tablo_adi1+".urunid"+"="+tablo_adi2+".urunid WHERE kullaniciadi=? ORDER BY siparisid DESC ",new String[] { Username });
        return n;

    }

    public Cursor UrunListele() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor veri=db.rawQuery("SELECT * FROM "+tablo_adi1,null);
        return veri;
    }
    public Cursor SiparisDurumu(int siparisid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor durumsor=db.rawQuery("SELECT iade FROM "+tablo_adi2+" WHERE siparisid=?",new String[]{String.valueOf(siparisid)});
        return  durumsor;

    }
    public Cursor SecilenSiparisVeriAl(int siparisid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor o=db.rawQuery("SELECT urunstok FROM "+tablo_adi2+" WHERE siparisid=?",new String[]{String.valueOf(siparisid)});
        return o;
    }
    public Cursor SecilenSiparisVeriAl2(int siparisid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor o=db.rawQuery("SELECT tutar FROM "+tablo_adi2+" WHERE siparisid=?",new String[]{String.valueOf(siparisid)});
        return o;
    }


    public void SiparisIadeEt(int siparisid){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(SiparisIade_alani,"TRUE");
        String sorgu=SiparisID_alani +" = " + siparisid;

        db.update(tablo_adi2,cv,sorgu,null);
        db.close();

    }

    public void VeriSil(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String sorgu=UrunID_alani +" = " + id;
        db.delete(tablo_adi1,sorgu,null);
        db.close();

    }

    public boolean Siparis_Ekleme(int musteriid,int siparisadet,double toplamtutar,int urunid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ID_alani,musteriid);
        cv.put(UrunID_alani,urunid);
        cv.put(SiparisAdedi_alani,siparisadet);
        cv.put(SiparisTutari_alani,toplamtutar);

        long result=db.insert(tablo_adi2,null,cv);


        if(result==-1){

            return false;
        }
        return true;

    }

    public boolean Urun_Ekleme(String urunadi, int urunadet,double urunfiyat )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(UrunAd_alani,urunadi);
        cv.put(UrunAlimAdeti_alani,urunadet);
        cv.put(UrunFiyat_alani,urunfiyat);

        long result=db.insert(tablo_adi1,null,cv);



        if(result==-1){

            return false;
        }
        return true;


    }


    public ArrayList<String> UrunleriGetir(){
        ArrayList<String> li=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        String Sor="SELECT urunadi FROM "+tablo_adi1;
        Cursor cur=db.rawQuery(Sor,null);


        try {
            if(cur.getCount()>0){
                while(cur.moveToNext()){
                    String uadi=cur.getString(cur.getColumnIndex("urunadi"));
                    li.add(uadi);


                }

            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {

            db.endTransaction();
            db.close();
        }



        return li;
    }





}
