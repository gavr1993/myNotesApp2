package com.example.mynotesapp;
public class SplashActivityInjector implements Injector<SplashActivity> {
    private Keystore keystore;
    public SplashActivityInjector(Keystore keystore) {
        this.keystore = keystore;
    }
    @Override
    public void inject(SplashActivity target) {
        target.setKeystore(keystore);
    }
}