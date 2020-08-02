package com.innova.phonetics;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.innova.phonetics.Custom.Custom;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class ConsonantsActivity extends AppCompatActivity {
    // Variables for storing views used in the layout
    private ImageView  backBtn;
    private GridLayout  conGridLayout;
    private ArrayList<View>  buttons;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consonants);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Get the layout of the consonant activity
        conGridLayout = findViewById(R.id.conGridLayout);
        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(homeActivityListener);

        buttons = Custom.findViewsByTag(conGridLayout, "con_sound");

        // For all the buttons, add the onclick listener
        // to enable playing of sounds
        for (View button : buttons){
            button.setOnClickListener(playConSoundListener);
        }
    }

    // A listener to go back to the homeActivity
    private View.OnClickListener homeActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onBackPressed();
        }
    };

    private View.OnClickListener playConSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playConSound(getApplicationContext(), v);
        }
    };
}
