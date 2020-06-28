package com.example.mynotesapp;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class DefaultActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        // noop
    }
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        // noop
    }
    @Override
    public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        // noop
    }
    @Override
    public void onActivityPreStarted(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPostStarted(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPreResumed(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPostResumed(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPrePaused(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPostPaused(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPreStopped(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPostStopped(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        // noop
    }
    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        // noop
    }
    @Override
    public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        // noop
    }
    @Override
    public void onActivityPreDestroyed(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        // noop
    }
    @Override
    public void onActivityPostDestroyed(@NonNull Activity activity) {
        // noop
    }
}