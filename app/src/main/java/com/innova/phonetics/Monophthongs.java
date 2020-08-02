package com.innova.phonetics;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.innova.phonetics.Custom.Custom;

import java.util.ArrayList;


public class Monophthongs extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monophthongs);
        GridLayout monGridLayout = findViewById(R.id.monGridLayout);
        ImageView backBtn = findViewById(R.id.back_btn);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        backBtn.setOnClickListener(homeActivityListener);

        ArrayList<View> buttons = Custom.findViewsByTag(monGridLayout, "mon_sound");
        for (View button : buttons){
            button.setOnClickListener(playMonSoundListener);
        }
    }

    private View.OnClickListener homeActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    private View.OnClickListener playMonSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playMonoSound(getApplicationContext(), v);
        }
    };
}
