package com.innova.phonetics;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import static maes.tech.intentanim.CustomIntent.customType;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        ImageView backBtn = findViewById(R.id.back_btn);
        CardView learn_introduction = findViewById(R.id.learn_introduction);
        CardView learn_monophthongs = findViewById(R.id.learn_monophthongs);
        CardView learn_diphthongs = findViewById(R.id.learn_diphthongs);
        CardView learn_consonants = findViewById(R.id.learn_consonants);

        backBtn.setOnClickListener(homeActivityListener);
        learn_introduction.setOnClickListener(introActivityListener);
        learn_monophthongs.setOnClickListener(learnMonoActivityListener);
        learn_diphthongs.setOnClickListener(learnDiphActivityListener);
        learn_consonants.setOnClickListener(learnConsActivityListener);
    }

    // Launches homeActivity
    private View.OnClickListener homeActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivityIntent);

            // Screen transition animation
            customType(LearnActivity.this, "right-to-left");
        }
    };


    // Launches introActivity
    private View.OnClickListener introActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent introActivityIntent = new Intent(getApplicationContext(), LearnIntroduction.class);
            startActivity(introActivityIntent);
            customType(LearnActivity.this, "left-to-right");
        }
    };

    // Launches learnMonoActivity
    private View.OnClickListener learnMonoActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent learnMonoActivityIntent = new Intent(getApplicationContext(), LearnMonophthongs.class);
            startActivity(learnMonoActivityIntent);
            customType(LearnActivity.this, "left-to-right");
        }
    };

    // Launches learnDipthActivity
    private View.OnClickListener learnDiphActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent learnDiphActivityIntent = new Intent(getApplicationContext(), LearnDiphthongs.class);
            startActivity(learnDiphActivityIntent);
            customType(LearnActivity.this, "left-to-right");
        }
    };

    // Launches learnConsActivity
    private View.OnClickListener learnConsActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent learnConsActivityIntent = new Intent(getApplicationContext(), LearnConsonants.class);
            startActivity(learnConsActivityIntent);
            customType(LearnActivity.this, "left-to-right");
        }
    };
}
