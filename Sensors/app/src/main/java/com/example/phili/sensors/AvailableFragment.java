package com.example.phili.sensors;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by phili on 31.10.2017.
 */


public class AvailableFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.available_fragment, container, false);
        final RadioGroup rg_all_available = rootView.findViewById(R.id.radioGroup_available);

        final TextView Output = (TextView) rootView.findViewById(R.id.textView_available_output);
        rg_all_available.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(getId()) {
                    case R.id.radioButton_all_available:
                        rg_all_available.clearCheck();
                            break;
                    case R.id.radioButton_proxy_available:
                        rg_all_available.clearCheck();
                            break;
                }
            }
        });


        return rootView;
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_all_available:
                if (checked)


                    break;
            case R.id.radioButton_proxy_available:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }
}