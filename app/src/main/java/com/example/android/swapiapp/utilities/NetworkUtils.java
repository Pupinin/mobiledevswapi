package com.example.android.swapiapp.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String SWAPI_BASE_URL = "https://swapi.co/api/";
    private final static String SWAPI_CATEGORIE_MOVIES_PARAM = "films";

    public static String getAllMoviesUrl(){
        return SWAPI_BASE_URL + SWAPI_CATEGORIE_MOVIES_PARAM;
    }
    public static String getMovieByIdUrl(int id){
        return SWAPI_BASE_URL + SWAPI_CATEGORIE_MOVIES_PARAM + '/' + id;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
