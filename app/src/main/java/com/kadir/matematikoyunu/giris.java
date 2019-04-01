package com.kadir.matematikoyunu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class giris extends AppCompatActivity {

    Button toplama;
    Button cikarma;
    Button carpma;
    Button bolme;
    Button karisik;
    Button master;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        toplama = (Button)findViewById(R.id.btn_toplama);
        toplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), seviye_toplama.class);
                startActivity(i);
                finish();
            }
        });

        cikarma = (Button)findViewById(R.id.btn_cikarma);
        cikarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), seviye_cikarma.class);
                startActivity(i);
                finish();
            }
        });

        carpma = (Button)findViewById(R.id.btn_carpma);
        carpma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), seviye_carpma.class);
                startActivity(i);
                finish();
            }
        });

        bolme = (Button)findViewById(R.id.btn_bolme);
        bolme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), seviye_bolme.class);
                startActivity(i);
                finish();
            }
        });

        karisik = (Button)findViewById(R.id.btn_karisik);
        karisik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), seviye_karisik.class);
                startActivity(i);
                finish();
            }
        });

        master = (Button)findViewById(R.id.btn_ayarlar);
        master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Master_seviye_secim.class);
                startActivity(i);
                finish();
            }
        });


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(giris.this);
        alert.setTitle(R.string.ci);
        alert.setMessage(R.string.soru);
        alert.setCancelable(true);
        alert.setNegativeButton(R.string.hayir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog al = alert.create();
        al.show();
    }
}
