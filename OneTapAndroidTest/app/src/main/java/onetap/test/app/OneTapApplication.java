package onetap.test.app;

import android.app.Application;

import timber.log.Timber;

public class OneTapApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
