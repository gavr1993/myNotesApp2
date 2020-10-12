package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private Keystore keystore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        setKeystore(keystore);
        if (keystore.hasPin()) intent = new Intent(SplashActivity.this,
                PinEnterActivity.class);
        else intent = new Intent(SplashActivity.this, PrefActivity.class);
        startActivity(intent);
        finish();
    }

    public void setKeystore(Keystore keystore) {
        this.keystore = keystore;
    }
}