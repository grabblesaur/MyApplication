package ru.bayar.bogdanov.myapplication;

import android.content.Context;

/**
 * Created by android on 16.11.17.
 */

public class Application extends android.app.Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(Context context) {
        return ((Application) context.getApplicationContext()).component;
    }
}
