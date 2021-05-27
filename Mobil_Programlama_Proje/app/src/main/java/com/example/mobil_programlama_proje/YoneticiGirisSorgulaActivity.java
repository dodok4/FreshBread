package com.example.mobil_programlama_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class YoneticiGirisSorgulaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici_giris_sorgula);

      final EditText et_admingirissorgula=(EditText)findViewById(R.id.et_admingirissorgula);
      final EditText et_admingirissifre=(EditText)findViewById(R.id.et_admingirissifre);

      Button btn_admingiris=(Button)findViewById(R.id.btn_admingiris);



      btn_admingiris.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String username = et_admingirissorgula.getText().toString();
              String password = et_admingirissifre.getText().toString();
              Intent gec;
              if (username.equals("admin")&&password.equals("1234")) {

                  gec = new Intent(getApplicationContext(), AdminEkraniActivity.class);
                  startActivity(gec);

              }
              else {
                  Toast.makeText(YoneticiGirisSorgulaActivity.this, "kullaniciadi=" + username + "sifre=" + password, Toast.LENGTH_SHORT).show();
              }

          }
      });



    }
}
