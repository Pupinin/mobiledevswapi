package com.example.android.swapiapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail_fragment, container, false);

        Bundle bundle = getArguments();
     //   TextView jsonView = view.findViewById(R.id.textViewJson);
        TextView titleView = view.findViewById(R.id.textViewTitle);
        TextView episodeIdView = view.findViewById(R.id.episodeIdTextView);
        TextView openingView = view.findViewById(R.id.openingTextView);
        TextView directorView = view.findViewById(R.id.directorTextView);
        TextView producerView = view.findViewById(R.id.producerTextView);
        TextView releaseDateView = view.findViewById(R.id.releaseDateTextView);

        String item = "";
        if (bundle != null) {
            item = getArguments().getString("item");
        }

        try {
            JSONObject jsonObject = new JSONObject(item);
            Movie movie = new MovieManager().parseMovie(jsonObject);

            titleView.setText(movie.getTitle());
            episodeIdView.setText(String.format("%s", movie.getEpisode_id()));
            openingView.setText(movie.getOpening_crawl());
            directorView.setText(movie.getDirector());
            producerView.setText(movie.getProducer());
            releaseDateView.setText(movie.getRelease_date());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // jsonView.setText(item);

        return view;
    }
}
