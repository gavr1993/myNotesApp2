package com.example.mynotesapp;
public class PrefActivityInjector implements Injector<PrefActivity> {
    private Keystore keystore;
    public PrefActivityInjector(Keystore keystore) {
        this.keystore = keystore;
    }
    @Override
    public void inject(PrefActivity target) {
        target.setKeystore(keystore);
    }
}
