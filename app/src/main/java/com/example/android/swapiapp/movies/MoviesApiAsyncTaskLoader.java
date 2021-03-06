package com.example.android.swapiapp.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class MoviesApiAsyncTaskLoader extends AsyncTaskLoader<String> {
    public MoviesApiAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return new MovieRepository().getAllFromApi();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
