package com.example.mobil_programlama_proje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Giris_Ekrani extends Activity {

    public void onCreate(Bundle ban){
        super.onCreate(ban);
        setContentView(R.layout.giris_ekrani);//giris ekranıyla ilişkilendirildi.

        Thread zaman=new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i("tago","giriş ekranı çalışmadı");//Log ile sadece geliştirme aşamasında bizim görebileceğimiz mesajlar alabiliyoruz
                }finally {
                    Intent i =new Intent(Giris_Ekrani.this,MainActivity.class);
                    startActivity(i);
                }
            }

        };//işlem class
        zaman.start();//Thread çalıştı manifestte gerekli değişiklikler yapıldı



    }



}
