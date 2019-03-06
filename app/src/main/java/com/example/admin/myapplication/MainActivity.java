package com.example.admin.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private Switch aSwitch;
    private TextInputLayout textUserName, textPassWord;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onSetUpview();
        onClickListener();
        loadLocale();
    }

    private void onSetUpview() {
        textUserName = findViewById(R.id.text_UserName);
        textPassWord = findViewById(R.id.text_PassWord);
        username = findViewById(R.id.UserName);
        password = findViewById(R.id.Password);
        videoView = findViewById(R.id.videoview);
        aSwitch = findViewById(R.id.switch1);

        setBackGroudVideo("https://storage.googleapis.com/coverr-main/mp4/Mt_Baker.mp4");
    }

    private void onClickListener() {
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changLanguage();

//                if (aSwitch.isChecked()) {
//                    setLocate("en");
//                    //recreate();
//                } else {
//                    setLocate("vi");
//                   // recreate();
//                }
////                finish();
            }

        });
    }

    private void changLanguage() {
        if (aSwitch.isChecked()) {
            setLocate("en");
            recreate();
        } else {
            setLocate("vi");
            recreate();
        }
    }

//    private void saveLanguage() {
//        //switch
//        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
//        String lang = preferences.getString("My_lang", "");
//        if (lang.equals("vi")) {
//            aSwitch.setChecked(false);
//        } else if (lang.equals("en")) {
//            aSwitch.setChecked(true);
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setBackGroudVideo(String url) {
        try {
            videoView.setVideoURI(Uri.parse(url));
            videoView.start();
            //Log.e("Url video", url);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            videoView.setBackgroundDrawable(null);
                        }
                    }, 100);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//        change language


    public void setLocate(String lang) {
        try {
            if (lang != null && !lang.equals("")) {
                Locale locale = new Locale(lang);
                Locale.setDefault(locale);
                Configuration configuration = new Configuration();
                configuration.locale = locale;
                getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                // save data
                SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
                editor.putString("My_lang", lang);
                editor.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLocale() {
        try {
            SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
            String lang = preferences.getString("My_lang", "");
            setLocate(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









