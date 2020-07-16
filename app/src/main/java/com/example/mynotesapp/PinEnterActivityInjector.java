package com.example.mynotesapp;

public class PinEnterActivityInjector implements Injector<PinEnterActivity> {
    private Keystore keystore;

    public PinEnterActivityInjector(Keystore keystore) {
        this.keystore = keystore;
    }

    @Override
    public void inject(PinEnterActivity target) {
        target.setKeystore(keystore);
    }
}