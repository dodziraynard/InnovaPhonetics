package com.innova.phonetics;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.innova.phonetics.Custom.Custom;

import java.util.ArrayList;

public class DiphthongsActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diphthongs);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        GridLayout diGridLayout = findViewById(R.id.diGridLayout);

        ImageView backBtn = findViewById(R.id.back_btn);
        ;
        backBtn.setOnClickListener(onClickListener);

        ArrayList<View> buttons = Custom.findViewsByTag(diGridLayout, "di_sound");
        for (View button : buttons) {
            button.setOnClickListener(playDiSoundListener);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    private View.OnClickListener playDiSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playDiSound(getApplicationContext(), v);
        }
    };
}
