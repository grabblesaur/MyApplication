package ru.bayar.bogdanov.myapplication.utils;


import android.location.Location;

public class LocationUtils {

    private LocationUtilsListener mListener;

    public void setLocation(Location location) {
        if (mListener != null) {
            mListener.onLocationChanged(location);
        }
    }

    public interface LocationUtilsListener {
        void onLocationChanged(Location location);
    }

    public void setOnClickListener(LocationUtilsListener listener) {
        mListener = listener;
    }
}
