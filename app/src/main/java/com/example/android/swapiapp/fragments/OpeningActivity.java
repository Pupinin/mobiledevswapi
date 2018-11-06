package com.example.android.swapiapp.fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.swapiapp.R;

import java.util.Locale;

public class OpeningActivity extends FragmentActivity {

    TextView openingView;
    TextToSpeech txt2Speech;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Initialising Modes Day/Night
        if (AppCompatDelegate.getDefaultNightMode()
                == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_activity);

        openingView = findViewById(R.id.openingTextView);

        String openingText = getIntent().getStringExtra("opening");

        if (openingText.isEmpty()) {
            openingView.setText("No args");
        }
        openingView.setText(openingText);


        Button listenButton = (Button) findViewById(R.id.textToSpeechButton);
        txt2Speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    txt2Speech.setLanguage(Locale.UK);
                }
            }
        });

        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = openingView.getText().toString();
                txt2Speech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    public void onPause(){
        if(txt2Speech !=null){
            txt2Speech.stop();
            txt2Speech.shutdown();
        }
        super.onPause();
    }
}
