package com.example.android.swapiapp.movies;

import com.example.android.swapiapp.utilities.NetworkUtils;

import java.io.IOException;

public class MovieRepository implements IRepository {
    @Override
    public String getAllFromApi() {
        try {
            return NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getAllMoviesUrl());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMovieFromApiById(int id){
        try {
            return NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getMovieByIdUrl(id));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAllFromDatabase() {
        return null;
    }
}
