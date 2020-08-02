package com.innova.phonetics.Custom;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.innova.phonetics.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.VIBRATOR_SERVICE;

public class Custom {

    // Declararing helper variables
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private static final int MY_PERMISSIONS_REQUEST_VIBRATE = 2;
    public static MediaRecorder mediaRecorder;

    // Returns all views in a layout with a particular tag.
    public static ArrayList<View> findViewsByTag(ViewGroup viewGroup, String tag){
        ArrayList<View> views = new ArrayList<>();

        // Get the number of views on the layout
        final int childCount = viewGroup.getChildCount();

        // Iterate over the views found in the layout
        for (int i = 0; i < childCount; i++) {
            final View view = viewGroup.getChildAt(i);

            // If the view found in contains over views
            if (view instanceof ViewGroup){
                // Recursively call the findViewsByTag function on the viewGroup
                views.addAll(findViewsByTag((ViewGroup) view, tag));
            }
            final Object tagObject = view.getTag();
            if (tagObject != null && tagObject.equals(tag)){
                views.add(view);
            }
        }
        return views;
    }

    // Helper function for playing the recorded sounds
    private static void playSound(Context context, int sound){
        MediaPlayer mp;
        mp = MediaPlayer.create(context, sound);
        if (mp.isPlaying()){
            mp.seekTo(0);
        } else {
            mp.start();
        }
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
    }

    // Records audio from the user
    public static void record(Activity activity, Context context, String filename){
        // Checking for permission from the user
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED){

            // If no permission request for it.
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.RECORD_AUDIO)){
                return;
            }

            else{
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            }

        } else {

            // Creating a new file on the internal storge.
            //This is to prevent audio players from having access to the recoreded voices
            File folder = new File(context.getFilesDir() + "/PhoneticsMaster");
            //File folder = new File(Environment.getExternalStorageDirectory() + "/PhoneticsMaster");
            if (!folder.exists()){
                folder.mkdir();
            }
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(folder+"/"+filename+".3gpp");
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try{
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IOException e){
                Toast.makeText(context, "Sorry, can't record", Toast.LENGTH_SHORT).show();
                Log.e("Record", e.getMessage());
            }
        }
    }

    public static void stopRecording(Context context, MediaRecorder recorder){
        try{
            recorder.stop();
            recorder.release();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Nope", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void playRecording(final Context context, String filename){
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final String fn = context.getFilesDir() + "/PhoneticsMaster/" +filename + ".3gpp";

        try{
            mediaPlayer.setDataSource(fn);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    Toast.makeText(context, "Playing Audio", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            Toast.makeText(context, "No audio", Toast.LENGTH_SHORT).show();
            Log.e("Play recording", e.getMessage());
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
    }

    public static void playConSound(Context context, View view){
        int id = view.getId();
        int sound;
        switch (id){
            case R.id.c1:
                sound = R.raw.c1;
                break;
            case R.id.c2:
                sound = R.raw.c2;
                break;
            case R.id.c3:
                sound = R.raw.c3;
                break;
            case R.id.c4:
                sound = R.raw.c4;
                break;
            case R.id.c5:
                sound = R.raw.c5;
                break;
            case R.id.c6:
                sound = R.raw.c6;
                break;
            case R.id.c7:
                sound = R.raw.c7;
                break;
            case R.id.c8:
                sound = R.raw.c8;
                break;
            case R.id.c9:
                sound = R.raw.c9;
                break;
            case R.id.c10:
                sound = R.raw.c10;
                break;
            case R.id.c11:
                sound = R.raw.c11;
                break;
            case R.id.c12:
                sound = R.raw.c12;
                break;
            case R.id.c13:
                sound = R.raw.c13;
                break;
            case R.id.c14:
                sound = R.raw.c14;
                break;
            case R.id.c15:
                sound = R.raw.c15;
                break;
            case R.id.c16:
                sound = R.raw.c16;
                break;
            case R.id.c17:
                sound = R.raw.c17;
                break;
            case R.id.c18:
                sound = R.raw.c18;
                break;
            case R.id.c19:
                sound = R.raw.c19;
                break;
            case R.id.c20:
                sound = R.raw.c20;
                break;
            case R.id.c21:
                sound = R.raw.c21;
                break;
            case R.id.c22:
                sound = R.raw.c22;
                break;
            case R.id.c23:
                sound = R.raw.c23;
                break;
            case R.id.c24:
                sound = R.raw.c24;
                break;
            default:
                sound = R.raw.c1;
        }

        Custom.playSound(context, sound);
    }


    public static void playMonoSound(Context context, View view){
        int id = view.getId();
        int sound;
        switch (id){
            case R.id.m1:
                sound = R.raw.m1;
                break;
            case R.id.m2:
                sound = R.raw.m2;
                break;
            case R.id.m3:
                sound = R.raw.m3;
                break;
            case R.id.m4:
                sound = R.raw.m4;
                break;
            case R.id.m5:
                sound = R.raw.m5;
                break;
            case R.id.m6:
                sound = R.raw.m6;
                break;
            case R.id.m7:
                sound = R.raw.m7;
                break;
            case R.id.m8:
                sound = R.raw.m8;
                break;
            case R.id.m9:
                sound = R.raw.m9;
                break;
            case R.id.m10:
                sound = R.raw.m10;
                break;
            case R.id.m11:
                sound = R.raw.m11;
                break;
            case R.id.m12:
                sound = R.raw.m12;
                break;
            default:
                sound = R.raw.m1;
        }

        Custom.playSound(context, sound);
    }

    public static void playDiSound(Context context, View view){
        int id = view.getId();
        int phoneme;
        switch (id){
            case R.id.d1:
                phoneme = R.raw.d1;
                break;
            case R.id.d2:
                phoneme = R.raw.d2;
                break;
            case R.id.d3:
                phoneme = R.raw.d3;
                break;
            case R.id.d4:
                phoneme = R.raw.d4;
                break;
            case R.id.d5:
                phoneme = R.raw.d5;
                break;
            case R.id.d6:
                phoneme = R.raw.d6;
                break;
            case R.id.d7:
                phoneme = R.raw.d7;
                break;
            case R.id.d8:
                phoneme = R.raw.d8;
                break;
            default:
                phoneme = R.raw.d1;
        }
        Custom.playSound(context, phoneme);
    }

    public static void makeVibration(Activity activity, Context context){
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.VIBRATE)!=PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.VIBRATE)){

            }else{
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.VIBRATE},
                        MY_PERMISSIONS_REQUEST_VIBRATE);
            }

        } else{
            Vibrator vibrator;
            vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);

            if (Build.VERSION.SDK_INT >= 26){
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            }else{
                vibrator.vibrate(50);
            }
        }
    }

    public static void playSampleText(Context context, String filename){
        int sound;
        switch (filename.toLowerCase()){
            case "air":
                sound = R.raw.air;
                break;
            case "bag":
                sound = R.raw.bag;
                break;

            case "bed":
                sound = R.raw.bed;
                break;

            case "bird":
                sound = R.raw.bird;
                break;

            case "boy":
                sound = R.raw.boy;
                break;

            case "cat":
                sound = R.raw.cat;
                break;

            case "church":
                sound = R.raw.church;
                break;

            case "cow":
                sound = R.raw.cow;
                break;

            case "dog":
                sound = R.raw.dog;
                break;

            case "door":
                sound = R.raw.door;
                break;

            case "ear":
                sound = R.raw.ear;
                break;

            case "eight":
                sound = R.raw.eight;
                break;

            case "eye":
                sound = R.raw.eye;
                break;

            case "far":
                sound = R.raw.far;
                break;

            case "fly":
                sound = R.raw.fly;
                break;

            case "go":
                sound = R.raw.go;
                break;

            case "good":
                sound = R.raw.good;
                break;

            case "hat":
                sound = R.raw.hat;
                break;

            case "key":
                sound = R.raw.key;
                break;

            case "love":
                sound = R.raw.love;
                break;

            case "man":
                sound = R.raw.man;
                break;

            case "now":
                sound = R.raw.now;
                break;

            case "on":
                sound = R.raw.on;
                break;

            case "pen":
                sound = R.raw.pen;
                break;

            case "red":
                sound = R.raw.red;
                break;

            case "shall":
                sound = R.raw.shall;
                break;

            case "sheep":
                sound = R.raw.sheep;
                break;

            case "ship":
                sound = R.raw.ship;
                break;

            case "shoot":
                sound = R.raw.shoot;
                break;

            case "show":
                sound = R.raw.show;
                break;

            case "sing":
                sound = R.raw.sing;
                break;

            case "sun":
                sound = R.raw.sun;
                break;

            case "tea":
                sound = R.raw.tea;
                break;

            case "teacher":
                sound = R.raw.teacher;
                break;

            case "the":
                sound = R.raw.the;
                break;

            case "thin":
                sound = R.raw.thin;
                break;

            case "tour":
                sound = R.raw.tour;
                break;

            case "up":
                sound = R.raw.up;
                break;

            case "van":
                sound = R.raw.van;
                break;

            case "vision":
                sound = R.raw.vision;
                break;

            case "wet":
                sound = R.raw.wet;
                break;

            case "yam":
                sound = R.raw.yam;
                break;

            case "zoom":
                sound = R.raw.zoom;
                break;
            case "june":
                sound = R.raw.june;
                break;
            case "yes":
                sound = R.raw.yes;
                break;
            default:
                sound = R.raw.air;
        }
        Custom.playSound(context, sound);
    }
}

