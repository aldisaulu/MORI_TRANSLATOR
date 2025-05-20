package com.aldinata.moritranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.aldinata.moritranslator.Menu.TentangMori;
import com.aldinata.moritranslator.Menu.Terjemahan;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default fragment
        loadFragment(new Terjemahan());

        // Handle item selection
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            if (item.getItemId() == R.id.nav_translate) {
                fragment = new Terjemahan();
            } else if (item.getItemId() == R.id.nav_about) {
                fragment = new TentangMori();
            }

            if (fragment != null) {
                loadFragment(fragment);
            }

            if (fragment != null) {
                loadFragment(fragment);
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}
