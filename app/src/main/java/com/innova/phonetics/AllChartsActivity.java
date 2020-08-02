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

public class AllChartsActivity extends AppCompatActivity {

    // Declaration of views used in the layout files
    ImageView backBtn;
    GridLayout allChartsGrid;
    ArrayList<View> monButtons;
    ArrayList<View> diButtons;
    ArrayList<View> conButtons;
    private AdView mAdView;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_charts);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Getting views from the layout files
        allChartsGrid   = findViewById(R.id.allChartsGrid);
        backBtn         = findViewById(R.id.back_btn);
        monButtons      = Custom.findViewsByTag(allChartsGrid, "mon_sound");
        diButtons       = Custom.findViewsByTag(allChartsGrid, "di_sound");
        conButtons      = Custom.findViewsByTag(allChartsGrid, "con_sound");

        //Launch home activity
        backBtn.setOnClickListener(homeActivityListener);

        //Iterate all monophthong buttons and add the playMonoSoundLister
        for (View button : monButtons){
            button.setOnClickListener(playMonSoundListener);
        }

        //Iterate all monophthong buttons and add the playDiSoundListener
        for (View button : diButtons){
            button.setOnClickListener(playDiSoundListener);
        }

        //Iterate all monophthong buttons and add the playConSoundListener
        for (View button : conButtons){
            button.setOnClickListener(playConSoundListener);
        }
    }

    // Definition of homeActivityListener
    View.OnClickListener homeActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onBackPressed();
        }
    };

    // Definition of playMonSoundListener
    View.OnClickListener playMonSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playMonoSound(getApplicationContext(), v);
        }
    };

    // Definition of playConSoundListener
    View.OnClickListener playConSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playConSound(getApplicationContext(), v);
        }
    };

    // Definition of playDiSoundListener
    View.OnClickListener playDiSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playDiSound(getApplicationContext(), v);
        }
    };
}
