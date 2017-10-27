package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.Feather52;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.MeasurementSet;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.data.EncoderData;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.data.ImuData;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.Sensor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service;

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
    private LineChart mLineView;


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
        mLineView = (LineChart) findViewById(R.id.feather52_line_chart);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add empty data
        mLineView.setData(data);

        XAxis xl = mLineView.getXAxis();
        //xl.setTypeface(mTfLight);
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = mLineView.getAxisLeft();
        //leftAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mLineView.getAxisRight();
        rightAxis.setEnabled(false);
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
                clearUi();
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
                clearUi();
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
                        ImuData imuData = ((Measurement<ImuData>)m).getData();
                        stringBuilder.append("accX=" + imuData.getAccX() + " ");
                        stringBuilder.append("accY=" + imuData.getAccY() + " ");
                        stringBuilder.append("accZ=" + imuData.getAccZ() + " ");
                        break;
                    case ENCODER:
                        EncoderData encoderData = ((Measurement<EncoderData>)m).getData();
                        stringBuilder.append("angle=" + encoderData.getAngle() + " ");
                        break;
                }

                appendLog(stringBuilder.toString());

                MeasurementSet<EncoderData> measurementSet = mFeather52.getEncoder().getCurrentMeasurementSet();
                if (measurementSet != null) {
                    Map<String, Entry> entries = measurementSet.getLastMeasurement().toEntries();
                    addEncoderEntry(entries.get(EncoderData.LABEL_ANGLE));
                }
            }
        }
    };

    private void appendLog(String content) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("hh:mm:ss");
        mLogView.setText(
                dateFormat.format(new Date()) + " $ " + content + "\n" + mLogView.getText().toString()
        );
    }

    private void addEncoderEntry(Entry e) {
        LineData data = mLineView.getLineData();
        if (data != null) {
            ILineDataSet set = data.getDataSetByIndex(0);

            if(set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            data.addEntry(e, 0);
            data.notifyDataChanged();
            mLineView.notifyDataSetChanged();
            mLineView.setVisibleXRangeMaximum(10000);
            mLineView.moveViewTo(e.getX(), e.getY(), YAxis.AxisDependency.LEFT);
        }
    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }

    private void clearUi() {
        mLogView.setText("");
        mLineView.clearValues();
        mLineView.notifyDataSetChanged();
        mLineView.invalidate();
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
