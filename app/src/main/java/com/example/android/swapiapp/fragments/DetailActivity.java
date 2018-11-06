package com.example.android.swapiapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.swapiapp.R;
import com.example.android.swapiapp.movies.Movie;
import com.example.android.swapiapp.movies.MovieManager;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //Initialising Modes Day/Night
        if (AppCompatDelegate.getDefaultNightMode()
                == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = new Bundle();
        bundle.putString("item", getIntent().getStringExtra("item"));

        //inflating fragment
        DetailFragment detailFragment = new DetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        detailFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.detail, detailFragment)
                .commit();
    }
}
