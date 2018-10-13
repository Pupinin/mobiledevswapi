package com.example.android.swapiapp.movies;

import java.util.ArrayList;

public class MovieManager {
    private ArrayList<Movie> movies;
    private IRepository moviesRepository;

    public MovieManager(IRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
        movies = null;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    
}
