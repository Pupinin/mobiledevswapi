package com.example.android.swapiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.swapiapp.movies.IRepository;
import com.example.android.swapiapp.movies.MovieManager;
import com.example.android.swapiapp.movies.MovieRepository;
import com.example.android.swapiapp.movies.MoviesApiAsyncTaskLoader;
import com.example.android.swapiapp.preferences.SettingsActivity;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>,
        SharedPreferences.OnSharedPreferenceChangeListener {

    //member variables
    private IRepository movieRepository;
    private MovieManager movieManager;
    private static final int MOVIE_LOADER_ID = 20;
    TextView sideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialising Modes Day/Night
        if (AppCompatDelegate.getDefaultNightMode()
                == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        //Initial & setContent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView stuff
        ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framePlaceHolder, fragment);
        fragmentTransaction.commit();

        //findViews
        sideView = findViewById(R.id.textView_side);

        //Repositories
        movieRepository = new MovieRepository();
        movieManager = new MovieManager();

        //async call to populate movieManager
      //  getMoviesFromLoader();

        //sharedPrefrences call
        setupSharedPrefences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    private void getMoviesFromLoader() {
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> githubSearchLoader = loaderManager.getLoader(MOVIE_LOADER_ID);
        if (githubSearchLoader == null) {
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            loaderManager.restartLoader(MOVIE_LOADER_ID, null, this);
        }
    }

    //SharedPreferences setup
    private void setupSharedPrefences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //getting value
        boolean side = sharedPreferences.getBoolean(getString(R.string.pref_show_side_key),
                getResources().getBoolean(R.bool.pref_show_bass_default));

        String sideText;
        if (side)
            sideText = "Light";
        else
            sideText = "Dark";

        sideView.setText(String.format("Side: %s", sideText));

        //register tot preferencechanged
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);


    }


    //Everything MENU related
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuButton) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    //SharedPreference implemented method
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String keySide = getString(R.string.pref_show_side_key);
        if (key.equals(keySide)) {
            boolean side = sharedPreferences.getBoolean(key,
                    getResources().getBoolean(R.bool.pref_show_bass_default));
            String sideText;
            if (side) {
                sideText = "Light";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                sideText = "Dark";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            sideView.setText(String.format("Side: %s", sideText));
        }
    }

}
