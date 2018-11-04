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


        MovieListFragment movieListFragment = new MovieListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.list, movieListFragment);
        fragmentTransaction.commit();


        DetailFragment detailFragment = new DetailFragment();
        if (findViewById(R.id.detail) != null) {
            fragmentManager.beginTransaction().replace(R.id.detail, detailFragment).commit();
        }


        sideView = findViewById(R.id.textView_side);


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
        //getting value
        boolean side = sharedPreferences.getBoolean(getString(R.string.pref_show_side_key),
                getResources().getBoolean(R.bool.pref_boolean));

//        String sideText;
//        if (side)
//            sideText = "Light";
//        else
//            sideText = "Dark";
//
//        sideView.setText(String.format("Side: %s", sideText));

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
            String sideText;
            if (side) {
                sideText = "Light";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                sideText = "Dark";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

//            sideView.setText(String.format("Side: %s", sideText));
        }
    }

}
