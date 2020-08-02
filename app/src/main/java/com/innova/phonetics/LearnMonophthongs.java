package com.innova.phonetics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.innova.phonetics.Custom.Custom;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class LearnMonophthongs extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_monophthongs);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView backBtn = findViewById(R.id.back_btn);
        LinearLayout monoLinearLayout = findViewById(R.id.mono_linear_layout);

        backBtn.setOnClickListener(homeActivityListener);

        ArrayList<View> buttons = Custom.findViewsByTag(monoLinearLayout, "mono_sound");
        ArrayList<View> sampleTexts = Custom.findViewsByTag(monoLinearLayout, "mono_sample_text");
        ArrayList<View> recButtons = Custom.findViewsByTag(monoLinearLayout, "rec");
        ArrayList<View> plyRecButtons = Custom.findViewsByTag(monoLinearLayout, "play");

        for (View button : buttons) {
            button.setOnClickListener(playMonoSoundListener);
        }

        for (View text : sampleTexts) {
            text.setOnClickListener(playSampleText);
        }

        for (View recButton : recButtons) {
            recButton.setOnTouchListener(touchRecListener);
        }

        for (View plyRecButton : plyRecButtons) {
            plyRecButton.setOnClickListener(plyListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private View.OnClickListener homeActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivityIntent = new Intent(getApplicationContext(), LearnActivity.class);
            startActivity(mainActivityIntent);
            customType(LearnMonophthongs.this, "right-to-left");
        }
    };

    private View.OnLongClickListener recordListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(LearnMonophthongs.this, "Recordin Starts", Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    private View.OnClickListener playMonoSoundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Custom.playMonoSound(getApplicationContext(), v);
        }
    };

    View.OnClickListener playSampleText = new View.OnClickListener() {
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
                    Custom.record(LearnMonophthongs.this, getApplicationContext(), button.getText().toString());
                    Custom.makeVibration(LearnMonophthongs.this, getApplicationContext());
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
        public void onClick(View v) {
            Button button = (Button) v;
            Custom.playRecording(getApplicationContext(), button.getText().toString());
        }
    };
}
