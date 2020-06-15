package com.example.mynotesapp;

import android.os.Bundle;

import android.preference.PreferenceFragment;

import java.util.prefs.Preferences;

public class PrefActivity extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);
    }
}
