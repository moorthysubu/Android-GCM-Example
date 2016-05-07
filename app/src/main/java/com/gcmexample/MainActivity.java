package com.gcmexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gcm_example.R;
import com.gcmexample.gcm.RegistrationIntentService;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private BroadcastReceiver mGcmTokenReceiver = null;

    private TextView mGcmToken = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        mGcmToken = (TextView) findViewById(R.id.gcm_token);
        mGcmToken.setText("gcm token \n " + sharedPreferences.getString(getString(R.string.gcm_id_key), ""));
        mGcmTokenReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                boolean sentToken = sharedPreferences.getBoolean(getResources().getString(R.string.sentTokenToServer), false);
                if (sentToken) {
                    mGcmToken.setText("gcm token \n " + sharedPreferences.getString(getString(R.string.gcm_id_key), ""));
                    // here call web api to update gcm token to server
                    //callWebAPI();
                }
            }
        };

        // if (checkPlayServices()) {
        // Start IntentService to register this application with GCM.
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        // }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(mGcmTokenReceiver,new IntentFilter(getString(R.string.msg_gcm_registered)));
    }


    /*private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, getResources().getInteger(R.integer.playservicesresolutionrequest)).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }*/
}
