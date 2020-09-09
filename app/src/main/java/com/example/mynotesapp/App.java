package com.example.mynotesapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
public class App extends Application {
    @SuppressWarnings("rawtypes")
    private Map<Type, Injector> factories = new HashMap<>();
    private Keystore keystore;
    private NoteRepository repository;
    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new DefaultActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            //noinspection rawtypes
            Injector injector = factories.get(activity.getClass());
            if (injector == null) {
                return;
            }
            //noinspection unchecked
            injector.inject(activity);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        keystore = new SimpleKeystore(getSharedPreferences("keystore", Context.MODE_PRIVATE));
        repository = new FileNoteRepository(this);
        registerFactories();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
    private void registerFactories() {
        factories.put(PrefActivity.class, new PrefActivityInjector(keystore));
        factories.put(SplashActivity.class, new SplashActivityInjector(keystore));
        factories.put(PinEnterActivity.class, new PinEnterActivityInjector(keystore));
        factories.put(NotesActivity.class, new NotesActivityInjector(repository));
        factories.put(NoteEditActivity.class, new NotesEditActivityInjector(repository));
    }
}