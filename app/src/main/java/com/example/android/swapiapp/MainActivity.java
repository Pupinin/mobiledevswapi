package com.example.android.swapiapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.android.swapiapp.preferences.SettingsActivity;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {
//    AppCompatActivity, FragmentActivity
    TextView sideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framePlaceHolder, fragment);
        fragmentTransaction.commit();

        //findViews
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



    //SharedPreference implemented method
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String keySide = getString(R.string.pref_show_side_key);
        if (key.equals(keySide)) {
            boolean side = sharedPreferences.getBoolean(key,
                    getResources().getBoolean(R.bool.pref_show_bass_default));
            String sideText;
            if (side)
                sideText = "Light";
            else
                sideText = "Dark";

            sideView.setText(String.format("Side: %s", sideText));
        }
    }
}
