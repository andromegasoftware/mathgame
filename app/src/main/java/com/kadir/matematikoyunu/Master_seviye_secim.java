package com.kadir.matematikoyunu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Master_seviye_secim extends AppCompatActivity {

    Button mix;
    Button zihinoyunu;
    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_seviye_secim);

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~1188508498"); //reklamı initialise etmek için
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mix = (Button)findViewById(R.id.btn_kolay);
        mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent i = new Intent(getApplicationContext(), mix_master.class);
                    startActivity(i);
                    finish();                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(getApplicationContext(), mix_master.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });

        zihinoyunu = (Button)findViewById(R.id.btn_corta);
        zihinoyunu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent i = new Intent(getApplicationContext(), zihin_oyunu.class);
                    startActivity(i);
                    finish();              }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(getApplicationContext(), zihin_oyunu.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
    }
    public void onBackPressed() {

        Intent in = new Intent(Master_seviye_secim.this, giris.class);
        finish();
        startActivity(in);

    }
}
