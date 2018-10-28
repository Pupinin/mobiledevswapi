package com.example.android.swapiapp.movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieManager {
    private String rawJsonString;


    public MovieManager() {
        rawJsonString = "";
    }

    public String getRawJsonString() {
        return rawJsonString;
    }

    public void setRawJsonString(String rawJsonString) {
        this.rawJsonString = rawJsonString;
    }


    public ArrayList<Movie> ParseMoviesToArrayListMovies(String jsonString){
        rawJsonString = jsonString;
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject json = null;
        try {

            json = new JSONObject(jsonString);
            JSONArray array = json.getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {
                movies.add(parseMovie(array.getJSONObject(i)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;

    }

    private Movie parseMovie(JSONObject jsonMovie){
        Movie result = new Movie();

        try {
            result.setTitle(jsonMovie.getString("title"));
            result.setEpisode_id(Float.parseFloat(jsonMovie.getString("episode_id")));
            result.setOpening_crawl(jsonMovie.getString("opening_crawl"));
            result.setDirector(jsonMovie.getString("director"));
            result.setProducer(jsonMovie.getString("producer"));
            result.setRelease_date(jsonMovie.getString("release_date"));

            result.setCharacters(jsonArrayToJsonArrayListString(jsonMovie.getJSONArray("characters")));
            result.setPlanets(jsonArrayToJsonArrayListString(jsonMovie.getJSONArray("planets")));
            result.setStarships(jsonArrayToJsonArrayListString(jsonMovie.getJSONArray("starships")));
            result.setVehicles(jsonArrayToJsonArrayListString(jsonMovie.getJSONArray("vehicles")));
            result.setSpecies(jsonArrayToJsonArrayListString(jsonMovie.getJSONArray("species")));


            result.setCreated(jsonMovie.getString("created"));
            result.setEdited(jsonMovie.getString("edited"));
            result.setUrl(jsonMovie.getString("url"));

        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    private ArrayList<String> jsonArrayToJsonArrayListString(JSONArray array) {

        ArrayList <String> list = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                list.add(array.getString(i));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return list;
        }
    }

}
