package com.innova.phonetics;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.innova.phonetics.Custom.Custom;

import java.util.ArrayList;
import static maes.tech.intentanim.CustomIntent.customType;

public class LearnConsonants extends AppCompatActivity {
    ImageView backBtn;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_consonants);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(homeActivityListener);
        LinearLayout conLinearLayout = findViewById(R.id.conLinearLayout);
        ArrayList<View> buttons = Custom.findViewsByTag(conLinearLayout, "con_sound");
        ArrayList<View> sampleTexts     = Custom.findViewsByTag(conLinearLayout, "con_text_sample");
        ArrayList<View> recButtons      = Custom.findViewsByTag(conLinearLayout, "rec");
        ArrayList<View> plyRecButtons   = Custom.findViewsByTag(conLinearLayout, "play_rec");

        for (View button : buttons){
            button.setOnClickListener(playConSoundListener);
        }
        for (View text : sampleTexts){
            text.setOnClickListener(playSampleText);
        }

        for (View recButton : recButtons){
            recButton.setOnTouchListener(touchRecListener);
        }

        for (View plyRecButton : plyRecButtons){
            plyRecButton.setOnClickListener(plyListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        backBtn.setOnClickListener(homeActivityListener);

    }

    private View.OnClickListener homeActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivityIntent = new Intent(getApplicationContext(), LearnActivity.class);
            startActivity(mainActivityIntent);
            customType(LearnConsonants.this, "right-to-left");
        }
    };

    private View.OnClickListener playConSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playConSound(getApplicationContext(), v);
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
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Button button = (Button) v;
                    Custom.record(LearnConsonants.this, getApplicationContext(), button.getText().toString());
                    Custom.makeVibration(LearnConsonants.this, getApplicationContext());
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
            Custom.playRecording(getApplicationContext(),  button.getText().toString());
        }
    };
}
