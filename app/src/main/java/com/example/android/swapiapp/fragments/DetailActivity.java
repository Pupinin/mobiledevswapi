package com.example.android.swapiapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.swapiapp.R;
import com.example.android.swapiapp.movies.Movie;
import com.example.android.swapiapp.movies.MovieManager;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String resultString = "";
        //Initialising Modes Day/Night
        if (AppCompatDelegate.getDefaultNightMode()
                == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //TextView jsonView = findViewById(R.id.textViewJson);
        TextView titleView = findViewById(R.id.textViewTitle);
        TextView episodeIdView = findViewById(R.id.episodeIdTextView);
       // TextView openingView = findViewById(R.id.openingTextView);
        TextView directorView = findViewById(R.id.directorTextView);
        TextView producerView = findViewById(R.id.producerTextView);
        TextView releaseDateView = findViewById(R.id.releaseDateTextView);

        String item = getIntent().getStringExtra("item");

        try {
            JSONObject jsonObject = new JSONObject(item);
            Movie movie = new MovieManager().parseMovie(jsonObject);

            titleView.setText(movie.getTitle());
            episodeIdView.setText(String.format("%s", movie.getEpisode_id()));
        //    openingView.setText(movie.getOpening_crawl());
            directorView.setText(movie.getDirector());
            producerView.setText(movie.getProducer());
            releaseDateView.setText(movie.getRelease_date());

            resultString += "Title : " + movie.getTitle() +"\n";
            resultString += "Episode : " + String.format("%s", movie.getEpisode_id()) +"\n";
            resultString += "Opening crawl : " + movie.getOpening_crawl() +"\n";
            resultString += "Director : " + movie.getDirector() +"\n";
            resultString += "Producer : " + movie.getProducer() +"\n";
            resultString += "Release : " + movie.getRelease_date() +"\n";

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button buttonOne = (Button) findViewById(R.id.button1);
        final String finalResultString = resultString;
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, finalResultString);
                startActivity(Intent.createChooser(sharingIntent, "Share info"));
            }
        });
    }
}
