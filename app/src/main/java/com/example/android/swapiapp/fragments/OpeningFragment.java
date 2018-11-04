package com.example.android.swapiapp.fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.swapiapp.R;

import java.util.Locale;

public class OpeningFragment extends Fragment {

    TextToSpeech txt2Speech;

    public OpeningFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflate
        View rootView = inflater.inflate(R.layout.fragment_opening, container, false);

        //arguments
        Bundle bundle = getArguments();

        //reference
        final TextView textView = (TextView) rootView.findViewById(R.id.openingTextView);

        //settext
        if(bundle != null) {
          String openingString =  getArguments().getString("opening");
            textView.setText(openingString);
        }
        textView.setText("No args");



//        Button listenButton = (Button) rootView.findViewById(R.id.textToSpeechButton);
//        txt2Speech = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status != TextToSpeech.ERROR) {
//                    txt2Speech.setLanguage(Locale.UK);
//                }
//            }
//        });
//
//        listenButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String toSpeak = textView.getText().toString();
//                txt2Speech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });


        return rootView;
    }
}
