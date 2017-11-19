package ru.bayar.bogdanov.myapplication.ui.contacts;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bayar.bogdanov.myapplication.Application;
import ru.bayar.bogdanov.myapplication.R;
import ru.bayar.bogdanov.myapplication.utils.LocationService;
import ru.bayar.bogdanov.myapplication.utils.LocationUtils;

public class ContactsFragment extends Fragment {

    private static final String TAG = ContactsFragment.class.getName();

    @BindView(R.id.contacts_counter)
    TextView mCounterTextView;
    @BindView(R.id.contacts_location)
    TextView mLocationTextView;

    @Inject
    LocationUtils mLocationUtils;

    private Intent mLocationIntent;
    private int counter = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        Application.getComponent(getActivity()).inject(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopService();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initViews();
    }

    private void initViews() {
        startService();
        mLocationUtils.setOnClickListener(new LocationUtils.LocationUtilsListener() {
            @Override
            public void onLocationChanged(Location location) {
                counter++;
                mCounterTextView.setText(String.valueOf(counter));
                mLocationTextView.setText(String.valueOf(location.getLatitude()) + ", " + String.valueOf(location.getLongitude()));
            }
        });
    }

    private void startService() {
        Log.i(TAG, "startService: ");
        mLocationIntent = new Intent(getActivity(), LocationService.class);
        getActivity().startService(mLocationIntent);
    }

    private void stopService() {
        Log.i(TAG, "stopService: ");
        if (mLocationIntent != null) {
            getActivity().stopService(mLocationIntent);
        }
    }
}
