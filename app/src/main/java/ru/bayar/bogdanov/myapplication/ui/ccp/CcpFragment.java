package ru.bayar.bogdanov.myapplication.ui.ccp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rilixtech.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bayar.bogdanov.myapplication.R;

/**
 * Created by android on 12.01.18.
 */

public class CcpFragment extends Fragment {

    private static final String TAG = "CCP";

    @BindView(R.id.ccp_code_picker)
    CountryCodePicker mCountryCodePicker;
    @BindView(R.id.ccp_phone_et)
    EditText mPhoneEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ccp, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mCountryCodePicker.registerPhoneNumberTextView(mPhoneEditText);

        mPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "getNumber: " + mCountryCodePicker.getNumber());
                if (mCountryCodePicker.isValid()) {
                    mPhoneEditText.setError(null);
                } else {
                    mPhoneEditText.setError("not valid");
                }
            }
        });
    }
}
