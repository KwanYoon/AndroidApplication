package io.github.kwanyoon.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // hides topmost action bar
        getSupportActionBar().hide();
        // going from splash to main
        final Intent i = new Intent(SplashActivity.this, MainActivity.class);
        // waiting 1000 ms to go from splash to main
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}