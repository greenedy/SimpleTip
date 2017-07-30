package ca.uottawa.greene.simpletip;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.preference.PreferenceManager;

import static ca.uottawa.greene.simpletip.R.id.toolbar;

public class PreferencesActivity extends PreferenceActivity {
private EditTextPreference defaultTipPerecentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
       // ((AppCompatActivity)getActivity())setSupportActionBar(toolbar);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

    }

    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);


            addPreferencesFromResource(R.xml.preferences);
           // Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
           // setSupportActionBar(toolbar);
          //  getSupportActionBar().setHomeButtonEnabled(true);
          //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           // final Preference DefaultTip = findPreference("defaultTip");
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
           // SharedPreferences.Editor editor = sharedPreferences.edit();
            if (key.equals("defaultTip")) {
              //  Preference pref = findPreference(key);

            }
        }
    }

}