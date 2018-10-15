package com.example.android.swapiapp.movies;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieManager {
    private ArrayList<Movie> movies;


    public MovieManager() {
        movies = null;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }



}
