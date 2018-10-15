package com.example.android.swapiapp.movies;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public String GetAllMoviesApi(){
        String result = moviesRepository.getAllFromApi();
        //parsing string to ArrayList function
        //ParseStringJsonToArrayListObject(result);
        return result;
    }



    //TODO 1 vervolledige deze functie zodat we een arraylist movies hebben
    private void ParseStringJsonToArrayListObject(String jsonString){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
