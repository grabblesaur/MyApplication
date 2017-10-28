package ru.bayar.bogdanov.myapplication.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bayar.bogdanov.myapplication.CustomItemSelectedListener;
import ru.bayar.bogdanov.myapplication.R;
import ru.bayar.bogdanov.myapplication.ui.cards.CardsFragment;
import ru.bayar.bogdanov.myapplication.ui.contacts.ContactsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_bottom_nav)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        replaceFragment(new CardsFragment(), CardsFragment.class.getName());
        mBottomNavigationView.setOnNavigationItemSelectedListener(new CustomItemSelectedListener() {
            @Override
            public boolean onItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.item_bottom_nav_cards) {
                    replaceFragment(new CardsFragment(), CardsFragment.class.getName());
                    return true;
                } else if (item.getItemId() == R.id.item_bottom_nav_contacts) {
                    replaceFragment(new ContactsFragment(), ContactsFragment.class.getName());
                    return true;
                } else {
                    return false;
                }
            }
        });
        mBottomNavigationView.setOnNavigationItemReselectedListener(item -> {
            // do nothing
        });
    }

    private void replaceFragment(Fragment fragment, String tag) {
        Log.i(TAG, "replaceFragment: " + tag);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment, tag)
                .commit();
    }
}
