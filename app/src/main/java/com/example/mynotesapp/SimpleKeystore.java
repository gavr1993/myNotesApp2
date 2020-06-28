package com.example.mynotesapp;

import android.content.SharedPreferences;

public class SimpleKeystore implements Keystore {
    private static final String PIN_KEY = "PIN_KEY";
    private SharedPreferences preferences;

    public SimpleKeystore(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean hasPin() {
        return preferences.contains(PIN_KEY);
    }

    @Override
    public boolean checkPin(String pin) {
        return preferences.getString(PIN_KEY, "").equals(pin);
    }

    @Override
    public void saveNew(String pin) {
        preferences.edit()
                .putString(PIN_KEY, pin)
                .apply();
    }
}