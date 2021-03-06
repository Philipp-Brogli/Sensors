package com.example.phili.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private List<Sensor> listSensors;
    private boolean all = false;
    private boolean proxy = false;
    private boolean accuracyB = false;
    private boolean value = false;
    private int choise = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new AvailableFragment();
                case 1: return new CapabilitiesFragment();
                case 2: return new ListenerFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================


    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_all_available:
                if (checked){
                    all=true;
                    proxy =false;
                    AvailabilityText();
                }
                break;
            case R.id.radioButton_proxy_available:
                if (checked) {
                    all = false;
                    proxy = true;
                    AvailabilityText();

                }

                break;
            case R.id.radioButton_listener_value:
                if (checked){
                    accuracyB=false;
                    value=true;
                }
                 break;
            case R.id.radioButton_listener_accuracy:
                if (checked){
                    accuracyB=true;
                    value=false;
                    Toast.makeText(getApplicationContext(), "Test",
                            Toast.LENGTH_SHORT).show();}
                break;
        }

    }

    public void selectButtonClicked(View view){
            Toast.makeText(getApplicationContext(), "button",
                    Toast.LENGTH_SHORT).show();

        if (choise==4){
            choise=1;
        }else{
            choise+=1;
        }
        CapabiolityText(choise);
     }

    public void AvailabilityText(){

        if(all){
            listSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        }else{
            listSensors = mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        }

        int text = listSensors.size();
        String strText = Integer.toString(text);
        TextView tv = (TextView) findViewById(R.id.textView_available_output);
        tv.setText(strText);
    }
    public void CapabiolityText(int choise){
        String Description="";
        String Value="";
        float text;
        switch(choise) {
            case 1:
                Description = "Power: ";
                text = mProximity.getPower();
                Value = Float.toString(text);

                break;
            case 2:
                Description = "Resolution: ";
                text = mProximity.getResolution();
                Value = Float.toString(text);
                break;
            case 3:
                Description = "Maximum Range: ";
                text = mProximity.getMaximumRange();
                Value = Float.toString(text);
                break;
            case 4:
                Description = "Minimal Delay: ";
                text = mProximity.getMinDelay();
                Value = Float.toString(text);
                break;
        }







        TextView tv = (TextView) findViewById(R.id.textView4);
        tv.setText(Description);
        TextView tv2 = (TextView) findViewById(R.id.textView);
        tv2.setText(Value);
    }
    public void ListenerText(String text){

        TextView tv = (TextView) findViewById(R.id.textView_listener_output);
        tv.setText(text);
    }



    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if(accuracyB){
            String strText = Integer.toString(accuracy);
            ListenerText(strText);
        }
    }

    public void onSensorChanged(SensorEvent event) {
        if(value) {
            float text = event.values[0];
            String strText = Float.toString(text);
            ListenerText(strText);
        }
    }
}
