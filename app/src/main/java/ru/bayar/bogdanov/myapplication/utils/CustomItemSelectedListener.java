package ru.bayar.bogdanov.myapplication.utils;


import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public abstract class CustomItemSelectedListener
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final long MIN_CLICK_INTERVAL = 500; //in millis
    private long lastClickTime = 0;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        long currentTime = SystemClock.elapsedRealtime();
        if (currentTime - lastClickTime > MIN_CLICK_INTERVAL) {
            lastClickTime = currentTime;
            return onItemSelected(item);
        } else {
            return false;
        }
    }

    public abstract boolean onItemSelected(MenuItem item);
}
