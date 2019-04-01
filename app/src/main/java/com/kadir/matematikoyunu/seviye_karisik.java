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

public class seviye_karisik extends AppCompatActivity {
    Button kolay;
    Button orta;
    Button zor;
    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seviye_karisik);

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~1188508498"); //reklamı initialise etmek için
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        kolay = (Button)findViewById(R.id.btn_kolay);
        kolay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent i = new Intent(getApplicationContext(), karisik_kolay.class);
                    startActivity(i);
                    finish();         }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(getApplicationContext(), karisik_kolay.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });

        orta = (Button)findViewById(R.id.btn_corta);
        orta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent i = new Intent(getApplicationContext(), karisik_orta.class);
                    startActivity(i);
                    finish();         }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(getApplicationContext(), karisik_orta.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });

        zor = (Button)findViewById(R.id.btn_zor);
        zor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent i = new Intent(getApplicationContext(), karisik_zor.class);
                    startActivity(i);
                    finish();        }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(getApplicationContext(), karisik_zor.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
    }
    public void onBackPressed() {

        Intent in = new Intent(seviye_karisik.this, giris.class);
        finish();
        startActivity(in);

    }
}
