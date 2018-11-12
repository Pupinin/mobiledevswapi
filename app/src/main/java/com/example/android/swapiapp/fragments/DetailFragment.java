package com.example.android.swapiapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.swapiapp.R;
import com.example.android.swapiapp.movies.Movie;
import com.example.android.swapiapp.movies.MovieManager;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_detail_fragment, container, false);
        Bundle bundle = getArguments();
        TextView titleView = view.findViewById(R.id.textViewTitle);
        TextView episodeIdView = view.findViewById(R.id.episodeIdTextView);
        TextView directorView = view.findViewById(R.id.directorTextView);
        TextView producerView = view.findViewById(R.id.producerTextView);
        TextView releaseDateView = view.findViewById(R.id.releaseDateTextView);

        String item = "";
        if (bundle != null) {
            item = getArguments().getString("item");
            MovieManager manager = new MovieManager();
            try {
                Movie movie = manager.parseMovie(new JSONObject(item));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        final String openingText;
        String resultString = "";

        try {
            JSONObject jsonObject = new JSONObject(item);
            Movie parsedMovie = new MovieManager().parseMovie(jsonObject);

            titleView.setText(parsedMovie.getTitle());
            episodeIdView.setText(String.format("%s", parsedMovie.getEpisode_id()));
            directorView.setText(parsedMovie.getDirector());
            producerView.setText(parsedMovie.getProducer());
            releaseDateView.setText(parsedMovie.getRelease_date());

            openingText = parsedMovie.getOpening_crawl();

            resultString += "Title : " + parsedMovie.getTitle() +"\n";
            resultString += "Episode : " + String.format("%s", parsedMovie.getEpisode_id()) +"\n";
            resultString += "Opening crawl : " + parsedMovie.getOpening_crawl() +"\n";
            resultString += "Director : " + parsedMovie.getDirector() +"\n";
            resultString += "Producer : " + parsedMovie.getProducer() +"\n";
            resultString += "Release : " + parsedMovie.getRelease_date() +"\n";

        } catch (JSONException e) {
            e.printStackTrace();
            return view;
        }


        //setting listener for read button
        Button readButton = (Button) view.findViewById(R.id.readOpeningButton);

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), OpeningActivity.class);
                intent.putExtra("opening", openingText);
                getActivity().startActivity(intent);
            }
        });

        //sharebutton
        Button buttonOne = (Button) view.findViewById(R.id.shareButton);
        final String finalResultString = resultString;
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, finalResultString);
                startActivity(Intent.createChooser(sharingIntent, "Share info"));
            }
        });


        return view;
    }
}
