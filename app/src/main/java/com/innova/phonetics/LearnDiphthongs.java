package com.innova.phonetics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.innova.phonetics.Custom.Custom;

import java.util.ArrayList;

public class LearnDiphthongs extends AppCompatActivity {
    ImageView backBtn;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_diphthongs);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        backBtn = findViewById(R.id.back_btn);
        LinearLayout diLinearLayout = findViewById(R.id.di_linear_layout);
        ArrayList<View> buttons = Custom.findViewsByTag(diLinearLayout, "di_sound");

        backBtn.setOnClickListener(homeActivityListener);

        for (View button : buttons) {
            button.setOnClickListener(playDiSoundListener);
        }

        ArrayList<View> sampleTexts = Custom.findViewsByTag(diLinearLayout, "di_text_sample");
        ArrayList<View> recButtons = Custom.findViewsByTag(diLinearLayout, "rec");
        ArrayList<View> plyRecButtons = Custom.findViewsByTag(diLinearLayout, "play_rec");

        for (View text : sampleTexts) {
            text.setOnClickListener(playSampleText);
        }

        for (View recButton : recButtons) {
            recButton.setOnTouchListener(touchRecListener);
        }

        for (View plyRecButton : plyRecButtons) {
            plyRecButton.setOnClickListener(plyListener);
        }
        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(homeActivityListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(homeActivityListener);
    }


    private View.OnClickListener homeActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    private View.OnClickListener playDiSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playDiSound(getApplicationContext(), v);
        }
    };

    private View.OnClickListener playSampleText = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView = (TextView) v;
            Custom.playSampleText(getApplicationContext(), textView.getText().toString());
        }
    };

    private View.OnTouchListener touchRecListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Button button = (Button) v;
                    Custom.record(LearnDiphthongs.this, getApplicationContext(), button.getText().toString());
                    Custom.makeVibration(LearnDiphthongs.this, getApplicationContext());
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    Custom.stopRecording(getApplicationContext(), Custom.mediaRecorder);
                    break;
            }
            return false;
        }
    };

    private View.OnClickListener plyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            Custom.playRecording(getApplicationContext(), button.getText().toString());
        }
    };
}
