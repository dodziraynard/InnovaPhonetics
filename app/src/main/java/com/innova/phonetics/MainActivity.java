package com.innova.phonetics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.innova.phonetics.Adapters.HomeIconAdapter;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        handleDeepLink();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        // Side bar drawable initialization
        drawer = findViewById(R.id.drawer_layout);
        Button hamberger_btn = findViewById(R.id.hamberger_btn);

        NavigationView navigationView = findViewById(R.id.nav_view);

        // Seting the icons on the home screen.
        RecyclerView icon_recylerView = findViewById(R.id.icon_recylerView);
        icon_recylerView.setLayoutManager(new GridLayoutManager(this, 3));

        // Button on the homeAcivity
        final String[] home_items = {"Monophthongs",
                "Diphthongs",
                "Consonants",
                "All Charts",
                "Phonetics",};

        // Corresponding backgrounds
        final Integer[] items_bg = {R.mipmap.monophthong_ic,
                R.mipmap.diphthong_ic,
                R.mipmap.consonants_ic,
                R.mipmap.all_charts_ic,
                R.mipmap.learn_pho_ic,
                R.mipmap.about_ic};

        // Use the custom adapter to populate the icons on a grid
        HomeIconAdapter homeIconAdapter =
                new HomeIconAdapter(this, home_items, items_bg);

        // Adding listener to each of the icons
        homeIconAdapter.setOnItemClickListener(
                new HomeIconAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View itemView, int position) {

                        // Launching a corresponding activity depending of the button the use clicks
                        switch (home_items[position]) {
                            case "Monophthongs":
                                Intent monophthongsIntent = new Intent(getApplicationContext(), Monophthongs.class);
                                startActivity(monophthongsIntent);
                                break;
                            case "Diphthongs":
                                Intent diphthongsIntent = new Intent(getApplicationContext(), DiphthongsActivity.class);
                                startActivity(diphthongsIntent);
                                break;
                            case "Consonants":
                                Intent consonantIntent = new Intent(getApplicationContext(), ConsonantsActivity.class);
                                startActivity(consonantIntent);
                                break;
                            case "All Charts":
                                Intent allChartstIntent = new Intent(getApplicationContext(), AllChartsActivity.class);
                                startActivity(allChartstIntent);
                                break;
                            case "Phonetics":
                                Intent learnIntent = new Intent(getApplicationContext(), LearnActivity.class);
                                startActivity(learnIntent);
                                break;
                        }
                    }
                });
        icon_recylerView.setAdapter(homeIconAdapter);
    }

    private void handleDeepLink() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("HRD", "getDynamicLink:onFailure", e);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
