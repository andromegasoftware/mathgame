package com.kadir.matematikoyunu;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Sonuc extends AppCompatActivity {

    TextView skore;

    int skors;
    String shareBody = "https://play.google.com/store/apps/details?id=com.kadir.matematikoyunu&hl=tr";
    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~1188508498"); //reklamı initialise etmek için
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/2090226859");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        skore = (TextView)findViewById(R.id.textView5);


        Intent i = getIntent();
        skors = i.getIntExtra("skor", 0);
        skore.setText(("" + skors).toString());

        Button cikis = (Button)findViewById(R.id.cikis);
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Sonuc.this);
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

                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            finish();     }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                finish();
                            }
                        });

                    }
                });
                AlertDialog al = alert.create();
                al.show();
            }
        });


        Button tekrarOyna = (Button) findViewById(R.id.tekrarOyna);
        tekrarOyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent i = new Intent(Sonuc.this, giris.class);
                    finish();
                    startActivity(i);    }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(Sonuc.this, giris.class);
                        finish();
                        startActivity(i);
                    }
                });
            }
        });

        Button paylas = (Button) findViewById(R.id.paylas);
        paylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Oyun");
                i.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(i, "Paylaş "));

            }
        });

        Button oyla = (Button)findViewById(R.id.oyla);
        oyla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market/details?id=com.company.kadir.matematikoyunu&hl=tr")));
                }
                catch (ActivityNotFoundException e)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kadir.matematikoyunu&hl=tr")));
                }


            }
        });

    }
}
