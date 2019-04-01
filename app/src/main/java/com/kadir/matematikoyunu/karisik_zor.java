package com.kadir.matematikoyunu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;
import java.util.Random;

public class karisik_zor extends AppCompatActivity {

    private AdView mAdView;
    Button soru;
    Button cevap_a;
    Button cevap_b;
    Button cevap_c;
    Button cevap_d;
    Random random = new Random();
    int sayi1;
    int sayi2;
    int sonuc;
    int yanlis_cevap;
    int yanlis_sik_1;
    int yanlis_sik_2;
    int yanlis_sik_3;
    int kullanici_cevap;

    int sik_1;
    int sik_2;
    int sik_3;
    int sik_4;

    int scor = 0;
    TextView skor;



    int hata = 3;
    TextView hatasayisi;


    Handler h = new Handler();

    MediaPlayer dogru;
    MediaPlayer yanlis;

    ImageView image_a;
    ImageView image_b;
    ImageView image_c;
    ImageView image_d;

    ProgressBar progressBar;
    int progress = 100;

    private static final long START_TIME_IN_MILIS = 25000;

    TextView sure;

    CountDownTimer countDownTimer;
    boolean mTimerRunning;
    long mTimeLeftINMillis = START_TIME_IN_MILIS;
    int progressStatus = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karisik_zor);


        MobileAds.initialize(this, "ca-app-pub-3206398076180977~1188508498");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3206398076180977/8684812993");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        cevap_a = (Button) findViewById(R.id.btn_cevap_a);
        cevap_b = (Button) findViewById(R.id.btn_cevap_b);
        cevap_c = (Button) findViewById(R.id.btn_cevap_c);
        cevap_d = (Button) findViewById(R.id.btn_cevap_d);

        skor = (TextView)findViewById(R.id.textView_skor);
        skor.setText(("" + scor).toString());


        sure = (TextView) findViewById(R.id.textView_sure);


        hatasayisi = (TextView)findViewById(R.id.textView_hata);
        hatasayisi.setText(("" + hata).toString());

        dogru = MediaPlayer.create(this, R.raw.correct);
        yanlis = MediaPlayer.create(this, R.raw.wrong);

        image_a = (ImageView)findViewById(R.id.imageView);
        image_b = (ImageView)findViewById(R.id.imageView2);
        image_c = (ImageView)findViewById(R.id.imageView3);
        image_d = (ImageView)findViewById(R.id.imageView4);

        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setScaleY(3f);



        oyunDevam();


        cevap_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevap_b.setEnabled(false);
                cevap_c.setEnabled(false);
                cevap_d.setEnabled(false);
                kullanici_cevap = Integer.valueOf(cevap_a.getText().toString());
                if (sonuc == kullanici_cevap) {

                    image_a.setImageResource(R.mipmap.correct);
                    image_a.setVisibility(View.VISIBLE);

                    dogru.start();

                    scor = scor + 10;
                    skor.setText(("" + scor).toString());

                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            oyunDevam();


                        }
                    }, 1500);


                } else {

                    image_a.setImageResource(R.mipmap.wrong);
                    image_a.setVisibility(View.VISIBLE);
                    yanlis.start();
                    scor = scor - 5;
                    skor.setText(("" + scor).toString());

                    hata--;
                    hatasayisi.setText(("" + hata).toString());

                    if(hata <= 0) {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(karisik_zor.this, Sonuc.class);
                                i.putExtra("skor", scor);
                                startActivity(i);
                                finish();

                            }
                        }, 1500);

                    }

                    else
                    {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                oyunDevam();

                            }
                        }, 1500);
                    }
                }

            }
        });

        cevap_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevap_a.setEnabled(false);
                cevap_c.setEnabled(false);
                cevap_d.setEnabled(false);
                kullanici_cevap = Integer.valueOf(cevap_b.getText().toString());
                if (sonuc == kullanici_cevap) {
                    image_b.setImageResource(R.mipmap.correct);
                    image_b.setVisibility(View.VISIBLE);

                    dogru.start();

                    scor = scor + 10;
                    skor.setText(("" + scor).toString());
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            oyunDevam();
                        }
                    }, 1500);

                } else {

                    image_b.setImageResource(R.mipmap.wrong);
                    image_b.setVisibility(View.VISIBLE);

                    yanlis.start();
                    scor = scor -5;
                    skor.setText(("" + scor).toString());

                    hata--;
                    hatasayisi.setText(("" + hata).toString());

                    if(hata <= 0) {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(karisik_zor.this, Sonuc.class);
                                i.putExtra("skor", scor);
                                startActivity(i);
                                finish();

                            }
                        }, 1500);

                    }

                    else
                    {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                oyunDevam();

                            }
                        }, 1500);
                    }
                }

            }
        });


        cevap_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevap_b.setEnabled(false);
                cevap_a.setEnabled(false);
                cevap_d.setEnabled(false);
                kullanici_cevap = Integer.valueOf(cevap_c.getText().toString());
                if (sonuc == kullanici_cevap) {
                    image_c.setImageResource(R.mipmap.correct);
                    image_c.setVisibility(View.VISIBLE);

                    dogru.start();

                    scor = scor + 10;
                    skor.setText(("" + scor).toString());
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            oyunDevam();
                        }
                    }, 1500);

                } else {
                    image_c.setImageResource(R.mipmap.wrong);
                    image_c.setVisibility(View.VISIBLE);

                    yanlis.start();
                    scor = scor -5;
                    skor.setText(("" + scor).toString());

                    hata--;
                    hatasayisi.setText(("" + hata).toString());

                    if(hata <= 0) {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(karisik_zor.this, Sonuc.class);
                                i.putExtra("skor", scor);
                                startActivity(i);
                                finish();

                            }
                        }, 1500);

                    }

                    else
                    {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                oyunDevam();

                            }
                        }, 1500);
                    }
                }

            }
        });


        cevap_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevap_b.setEnabled(false);
                cevap_c.setEnabled(false);
                cevap_a.setEnabled(false);
                kullanici_cevap = Integer.valueOf(cevap_d.getText().toString());
                if (sonuc == kullanici_cevap) {
                    image_d.setImageResource(R.mipmap.correct);
                    image_d.setVisibility(View.VISIBLE);

                    dogru.start();

                    scor = scor + 10;
                    skor.setText(("" + scor).toString());
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            oyunDevam();
                        }
                    }, 1500);

                } else {
                    image_d.setImageResource(R.mipmap.wrong);
                    image_d.setVisibility(View.VISIBLE);

                    yanlis.start();
                    scor = scor -5;
                    skor.setText(("" + scor).toString());

                    hata--;
                    hatasayisi.setText(("" + hata).toString());


                    if(hata <= 0)
                    {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(karisik_zor.this, Sonuc.class);
                                i.putExtra("skor", scor);
                                startActivity(i);
                                finish();
                            }
                        }, 1500);

                    }



                    else
                    {
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                oyunDevam();

                            }
                        }, 1500);
                    }
                }

            }
        });

    }

    public void oyunDevam()
    {
        if(mTimerRunning)
        {
            pauseTimer();
        }
        else
        {
            startTimer();
            progressBar.setProgress(progressStatus);
        }

        image_a.setVisibility(View.INVISIBLE);
        image_b.setVisibility(View.INVISIBLE);
        image_c.setVisibility(View.INVISIBLE);
        image_d.setVisibility(View.INVISIBLE);

        cevap_b.setEnabled(true);
        cevap_c.setEnabled(true);
        cevap_a.setEnabled(true);
        cevap_d.setEnabled(true);

        int karisik_islem_secimi = random.nextInt(4)+1;

        sayi1 = random.nextInt(50)+50;
        sayi2 = random.nextInt(50)+50;
        yanlis_cevap = random.nextInt(3) + 1;

        sik_1 = random.nextInt(4) + 1;
        sik_2 = random.nextInt(4) + 1;
        while (sik_1 == sik_2)
        {
            sik_2 = random.nextInt(4) + 1;
        }
        sik_3 = random.nextInt(4) + 1;
        while (sik_1 == sik_3 || sik_2 == sik_3)
        {
            sik_3 = random.nextInt(4) + 1;
        }
        sik_4 = random.nextInt(4) + 1;
        while (sik_1 == sik_4 || sik_2 == sik_4 || sik_3 == sik_4)
        {
            sik_4 = random.nextInt(4) + 1;
        }

        switch (karisik_islem_secimi)
        {
            case 1: sonuc = sayi1 + sayi2; break;

            case 2:
                if(sayi1 >= sayi2)
                {
                    sonuc = sayi1 - sayi2;
                }
                else
                {
                    sonuc = sayi2 - sayi1;
                }
                break;
            case 3: {
                sayi1 = random.nextInt(10)+10;
                sayi2 = random.nextInt(15)+10;
                sonuc = sayi1 * sayi2;}
            break;

            case 4: {
                sayi2 = random.nextInt(9) + 5;
                sayi1 = (random.nextInt(10) + 5) * sayi2;
                sonuc = sayi1 / sayi2;
            } break;
        }

        yanlis_sik_1 = sonuc + yanlis_cevap;
        yanlis_sik_2 = sonuc - yanlis_cevap;
        yanlis_sik_3 = sonuc + yanlis_cevap + 1;



        soru = (Button) findViewById(R.id.btn_soru);
        switch (karisik_islem_secimi)
        {
            case 1: soru.setText((sayi1 + " + " + sayi2).toString()); break;

            case 2:
                if(sayi1 >= sayi2)
                {
                    soru.setText((sayi1 + " - " + sayi2).toString());
                }
                else
                {
                    soru.setText((sayi2 + " - " + sayi1).toString());
                }
                break;
            case 3:  soru.setText((sayi1 + " x " + sayi2).toString()); break;

            case 4:
                soru.setText((sayi1 + " ÷ " + sayi2).toString());
                break;
        }



        switch (sik_1)
        {
            case 1: cevap_a.setText(String.valueOf(sonuc)); break;
            case 2: cevap_a.setText(String.valueOf(yanlis_sik_1)); break;
            case 3: cevap_a.setText(String.valueOf(yanlis_sik_2)); break;
            case 4: cevap_a.setText(String.valueOf(yanlis_sik_3)); break;
        }
        switch (sik_2)
        {
            case 1: cevap_b.setText(String.valueOf(sonuc)); break;
            case 2: cevap_b.setText(String.valueOf(yanlis_sik_1)); break;
            case 3: cevap_b.setText(String.valueOf(yanlis_sik_2)); break;
            case 4: cevap_b.setText(String.valueOf(yanlis_sik_3)); break;
        }
        switch (sik_3)
        {
            case 1: cevap_c.setText(String.valueOf(sonuc)); break;
            case 2: cevap_c.setText(String.valueOf(yanlis_sik_1)); break;
            case 3: cevap_c.setText(String.valueOf(yanlis_sik_2)); break;
            case 4: cevap_c.setText(String.valueOf(yanlis_sik_3)); break;
        }
        switch (sik_4)
        {
            case 1: cevap_d.setText(String.valueOf(sonuc)); break;
            case 2: cevap_d.setText(String.valueOf(yanlis_sik_1)); break;
            case 3: cevap_d.setText(String.valueOf(yanlis_sik_2)); break;
            case 4: cevap_d.setText(String.valueOf(yanlis_sik_3)); break;
        }
        System.out.println(karisik_islem_secimi);

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(karisik_zor.this);
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

                pauseTimer();

                Intent in = new Intent(karisik_zor.this, giris.class);
                finish();
                startActivity(in);
            }
        });

        AlertDialog al = alert.create();
        al.show();
    }
    public void resetTimer()
    {
        mTimeLeftINMillis = START_TIME_IN_MILIS;
        updateCountDownText();


    }
    public void startTimer()
    {
        countDownTimer = new CountDownTimer(mTimeLeftINMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftINMillis = millisUntilFinished;
                updateCountDownText();
                progressStatus = progressStatus - 4;
                progressBar.setProgress(progressStatus);
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;

                yanlis.start();
                cevap_b.setEnabled(false);
                cevap_c.setEnabled(false);
                cevap_a.setEnabled(false);
                cevap_d.setEnabled(false);

                soru.setText(R.string.doldu);

                hata--;
                hatasayisi.setText(("" + hata).toString());

                pauseTimer();
                resetTimer();
                updateCountDownText();

                if(hata <= 0) {
                    pauseTimer();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent i = new Intent(karisik_zor.this, Sonuc.class);
                            i.putExtra("skor", scor);
                            startActivity(i);
                            finish();

                        }
                    }, 1500);

                }
                else
                {
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            oyunDevam();

                        }
                    }, 1500);
                }

            }
        }.start();

        mTimerRunning = true;
    }

    public void updateCountDownText()
    {

        int seconds = (int) (mTimeLeftINMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);

        sure.setText(timeLeftFormatted);
        progressBar.setProgress(progressStatus);
    }

    public void pauseTimer()
    {
        countDownTimer.cancel();
        mTimerRunning = false;
        progressStatus = 100;
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTimer();
    }



}
