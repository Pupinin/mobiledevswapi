package com.example.android.swapiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.swapiapp.fragments.DetailFragment;
import com.example.android.swapiapp.fragments.MovieListFragment;
import com.example.android.swapiapp.preferences.SettingsActivity;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {


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


        //Inflates MovieFragment
        MovieListFragment movieListFragment = new MovieListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.list, movieListFragment);
        fragmentTransaction.commit();


        //when in landscape checks if detail fragment is present
        DetailFragment detailFragment = new DetailFragment();
        if (findViewById(R.id.detail) != null) {
            fragmentManager.beginTransaction().replace(R.id.detail, detailFragment).commit();
        }


        //sharedPrefrences call
        setupSharedPrefences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }


    //Everything MENU related - start
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
    // stop

    //SharedPreferences setup
    private void setupSharedPrefences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //register tot preferencechanged
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    //SharedPreference implemented method
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String keySide = getString(R.string.pref_show_side_key);
        if (key.equals(keySide)) {
            boolean side = sharedPreferences.getBoolean(key,
                    getResources().getBoolean(R.bool.pref_boolean));
            if (side) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }

}
