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
        //  TextView openingView = view.findViewById(R.id.openingTextView);
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

        try {
            JSONObject jsonObject = new JSONObject(item);
            Movie parsedMovie = new MovieManager().parseMovie(jsonObject);

            titleView.setText(parsedMovie.getTitle());
            episodeIdView.setText(String.format("%s", parsedMovie.getEpisode_id()));
            //    openingView.setText(movie.getOpening_crawl());
            directorView.setText(parsedMovie.getDirector());
            producerView.setText(parsedMovie.getProducer());
            releaseDateView.setText(parsedMovie.getRelease_date());

            openingText = parsedMovie.getOpening_crawl();

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

//                OpeningFragment openingFragment = new OpeningFragment();
//                Bundle args = new Bundle();
//                args.putString("opening", finalOpeningText);
//                openingFragment.setArguments(args);
//
//                getFragmentManager().beginTransaction().add(R.id.opening_container, openingFragment).commit();
            }
        });


        return view;
    }
}
