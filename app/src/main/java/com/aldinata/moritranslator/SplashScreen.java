package com.aldinata.moritranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.aldinata.moritranslator.Login.Login;

public class SplashScreen extends AppCompatActivity {

    private ImageView ivIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivIcon = findViewById(R.id.ivIcon);

        Animation animation_naik = AnimationUtils.loadAnimation(this, R.anim.transisi_naik);

        ivIcon.setAnimation(animation_naik);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.SYSTEM_UI_FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(Login.SHARED_PREF_NAME, 0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);

                if (hasLoggedIn){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        }, 3000);

    }
}