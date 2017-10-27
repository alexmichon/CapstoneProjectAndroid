package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.Feather52;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.IMU;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service;

/**
 * Created by Alex on 25/10/2017.
 */

public class ImuActivity extends AppCompatActivity {

    private static final String TAG = ImuActivity.class.getSimpleName();

    public static final String EXTRA_SENSOR_ID = "EXTRA_SENSOR_ID";

    private Feather52Service mFeather52Service;

    private final Feather52 mFeather52 = CapstoneProjectAndroidApplication.getInstance().getFeather52();
    private IMU mImu;

    @Override
    protected void onStart() {
        super.onStart();
        Intent gattServiceIntent = new Intent(this, Feather52Service.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imu);

        final Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_SENSOR_ID, -1);
        mImu = (IMU) mFeather52.getSensor(id);
        if (mImu == null) {
            Log.e(TAG, "Invalid sensor id:" + id);
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mImuReceiver, getIntentFilter());
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mImuReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
        }
        mFeather52Service = null;
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "Service connected");
            mFeather52Service = ((Feather52Service.LocalBinder) iBinder).getService();
            if (!mFeather52Service.initialize()) {
                Log.e(TAG, "Feather52 not initialized");
                finish();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "Service disconnected");
            mFeather52Service = null;
        }
    };

    private final BroadcastReceiver mImuReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            Log.d(TAG, "Receiving intent with action: " + action);
            if (Feather52Service.ACTION_GATT_CONNECTED.equals(action)) {
                invalidateOptionsMenu();
            }
            else if (Feather52Service.ACTION_GATT_DISCONNECTED.equals(action)) {
                invalidateOptionsMenu();
            }
            else if (Feather52Service.ACTION_DATA_AVAILABLE.equals(action)) {
            }
        }
    };

    private static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(Feather52Service.ACTION_GATT_CONNECTED);
        intentFilter.addAction(Feather52Service.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(Feather52Service.ACTION_DATA_AVAILABLE);

        return intentFilter;
    }
}
