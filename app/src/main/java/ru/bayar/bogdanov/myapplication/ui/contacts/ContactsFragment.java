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
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bayar.bogdanov.myapplication.Application;
import ru.bayar.bogdanov.myapplication.R;
import ru.bayar.bogdanov.myapplication.utils.LocationService;
import ru.bayar.bogdanov.myapplication.utils.LocationUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ContactsFragment extends Fragment {

    private static final String TAG = ContactsFragment.class.getName();

    @BindView(R.id.contacts_counter)
    TextView mCounterTextView;
    @BindView(R.id.contacts_location)
    TextView mLocationTextView;
    @BindView(R.id.contacts_timer_tv)
    TextView mTimerTextView;
    @BindView(R.id.contacts_timer_btn)
    Button mTimerButton;

    @Inject
    LocationUtils mLocationUtils;

    private Intent mLocationIntent;
    private int counter = 0;
    private boolean mIsStarted;
    private Subscription mSubscription;
    private int mTime = 60;

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

        mTimerButton.setOnClickListener(v -> {
            if (!mIsStarted) {
                mIsStarted = true;
                mTimerButton.setText("stop");
                mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                mTime--;
                                mTimerTextView.setText(String.valueOf(mTime));
                            }
                        });
            } else {
                mIsStarted = false;
                mTimerButton.setText("start");
                mTimerTextView.setText("");
                mSubscription.unsubscribe();
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
