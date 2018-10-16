package com.example.android.swapiapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.android.swapiapp.movies.IRepository;
import com.example.android.swapiapp.movies.MovieManager;
import com.example.android.swapiapp.movies.MovieRepository;
import com.example.android.swapiapp.movies.MoviesApiAsyncTaskLoader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {

    private IRepository movieRepository;
    private MovieManager movieManager;
    private static final int MOVIE_LOADER_ID = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepository = new MovieRepository();
        movieManager = new MovieManager();

        //async call to populate movieManager
        getMoviesFromLoader();
    }

    private void getMoviesFromLoader(){


        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> githubSearchLoader = loaderManager.getLoader(MOVIE_LOADER_ID);
        if (githubSearchLoader == null) {
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            loaderManager.restartLoader(MOVIE_LOADER_ID, null, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Async call to populate movieManager
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new MoviesApiAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        movieManager.setMovies(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
