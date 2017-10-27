package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.Feather52;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.MeasurementSet;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.IMUValue;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.Sensor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_DATA;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_SENSOR_ID;

/**
 * Created by Alex on 25/10/2017.
 */

public class Feather52Activity extends AppCompatActivity {

    private static final String TAG = Feather52Activity.class.getSimpleName();

    public static final String EXTRA_DEVICE_NAME = "EXTRA_FEATHER52";
    public static final String EXTRA_DEVICE_ADDRESS = "EXTRA_FEATHER52_ADDRESS";

    private final Feather52 mFeather52 = CapstoneProjectAndroidApplication.getInstance().getFeather52();

    private Feather52Service mFeather52Service;
    private String mDeviceName;
    private String mDeviceAddress;
    private boolean mConnected;
    private boolean mStarted;

    private TextView mLogView;

    @Override
    protected void onStart() {
        super.onStart();
        Intent gattServiceIntent = new Intent(this, Feather52Service.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feather52);

        final Feather52 feather52 = CapstoneProjectAndroidApplication.getInstance().getFeather52();

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRA_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRA_DEVICE_ADDRESS);

        setTitle(mDeviceName);

        mLogView = (TextView) findViewById(R.id.feather52_log_textview);
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mFeather52Receiver, getIntentFilter());
        if (mFeather52Service != null) {
            final boolean result = mFeather52Service.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mFeather52Receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
        }
        mFeather52Service = null;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Feather52Activity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feather52, menu);
        if (mConnected) {
            menu.findItem(R.id.feather52_menu_start).setVisible(!mStarted);
            menu.findItem(R.id.feather52_menu_stop).setVisible(mStarted);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.feather52_menu_start:
                mStarted = true;
                mFeather52Service.startRecording();
                invalidateOptionsMenu();
                return true;
            case R.id.feather52_menu_stop:
                mStarted = false;
                mFeather52Service.stopRecording();
                invalidateOptionsMenu();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "Service connected");
            mFeather52Service = ((Feather52Service.LocalBinder) iBinder).getService();
            if (!mFeather52Service.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }

            mFeather52Service.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "Service disconnected");
            mFeather52Service = null;
        }
    };



    private final BroadcastReceiver mFeather52Receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            Log.d(TAG, "Receiving intent with action: " + action);
            if (Feather52Service.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                invalidateOptionsMenu();
            }
            else if (Feather52Service.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                invalidateOptionsMenu();
            }
            else if (Feather52Service.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
            }
            else if (Feather52Service.ACTION_DATA_AVAILABLE.equals(action)) {
                int sensorId = intent.getIntExtra(EXTRA_SENSOR_ID, -1);
                Sensor sensor = mFeather52.getSensor(sensorId);
                if (sensor == null) { return; }

                MeasurementSet ms = sensor.getCurrentMeasurementSet();
                if (ms == null) { return; }

                Measurement m = ms.getLastMeasurement();
                if (m == null) { return; }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(sensor.getName());
                stringBuilder.append(": ");
                stringBuilder.append("time=" + m.tookAt() + " ");
                switch(sensor.getType()) {
                    case IMU:
                        IMUValue imuValue = ((Measurement<IMUValue>)m).getValue();
                        stringBuilder.append("accX=" + imuValue.getAccX() + " ");
                        stringBuilder.append("accY=" + imuValue.getAccY() + " ");
                        stringBuilder.append("accZ=" + imuValue.getAccZ() + " ");
                        break;
                    case ENCODER:
                        int angle = ((Measurement<Integer>)m).getValue();
                        stringBuilder.append("angle=" + angle + " ");
                        break;
                }

                appendLog(stringBuilder.toString());
            }
        }
    };

    private void appendLog(String content) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("hh:mm:ss");
        mLogView.setText(
                dateFormat.format(new Date()) + " $ " + content + "\n" + mLogView.getText().toString()
        );
    }

    private static IntentFilter getIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(Feather52Service.ACTION_GATT_CONNECTED);
        intentFilter.addAction(Feather52Service.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(Feather52Service.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(Feather52Service.ACTION_DATA_AVAILABLE);

        return intentFilter;
    }
}
