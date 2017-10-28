package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.Feather52;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.TestExercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers.ExerciseHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers.MetricHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_LABEL;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_TOOK_AT;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_VALUE;

/**
 * Created by Alex on 25/10/2017.
 */

public class Feather52Activity extends AppCompatActivity {

    private static final String TAG = Feather52Activity.class.getSimpleName();

    public static final String EXTRA_DEVICE_NAME = "EXTRA_FEATHER52";
    public static final String EXTRA_DEVICE_ADDRESS = "EXTRA_FEATHER52_ADDRESS";

    private final Feather52 mFeather52 = CapstoneProjectAndroidApplication.getInstance().getFeather52();
    private final Handler mHandler = new Handler();

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
                startTestExercise();
                invalidateOptionsMenu();
                return true;
            case R.id.feather52_menu_stop:
                stopTestExercise();
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
                StringBuilder stringBuilder = new StringBuilder();

                String label = intent.getStringExtra(EXTRA_LABEL);
                stringBuilder.append(label + ": ");

                long tookAt = intent.getLongExtra(EXTRA_TOOK_AT, 0);
                stringBuilder.append("time=" + tookAt + " ");

                float value = intent.getFloatExtra(EXTRA_VALUE, 0);
                stringBuilder.append("value=" + value);

                appendLog(stringBuilder.toString());
            }
        }
    };


    private void startTestExercise() {
        mStarted = true;
        final Exercise exercise = new TestExercise();
        mFeather52.addExercise(exercise);
        exercise.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ExerciseHelper.create(exercise);
                for (Metric m: exercise.getMetrics().values()) {
                    MetricHelper.create(exercise, m);
                }
                mFeather52Service.startRecording();
            }
        }).start();
    }


    private void stopTestExercise() {
        mStarted = false;
        mFeather52Service.stopRecording();
    }


    private void appendLog(String content) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("HH:mm:ss");
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
