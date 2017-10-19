package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.adapters.BluetoothDeviceAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private BluetoothAdapter mBtAdapter;

    private BluetoothDeviceAdapter mDevicesAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDevicesAdapter = new BluetoothDeviceAdapter(this, R.layout.bluetooth_device);

        mListView = (ListView) findViewById(R.id.list_bluetooth_devices);
        mListView.setAdapter(mDevicesAdapter);
        mListView.setOnItemClickListener(mDeviceClickListener);

        Button scanButton = (Button) findViewById(R.id.button_bluetooth_scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "doDiscovery()");

                // If we're already discovering, stop it
                if (mBtAdapter.isDiscovering()) {
                    mBtAdapter.cancelDiscovery();
                }

                mDevicesAdapter.clear();
                mDevicesAdapter.notifyDataSetChanged();

                mBtAdapter.startDiscovery();
                view.setEnabled(false);
            }
        });

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        populatePairedDevices();
    }


    private void populatePairedDevices() {
        Log.d(TAG, "Populating paired devices");

        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            mDevicesAdapter.addAll(pairedDevices);
        }

        if (pairedDevices.size() == 0) {
            Toast.makeText(this, "No paired device", Toast.LENGTH_SHORT);
        }

        mDevicesAdapter.notifyDataSetChanged();
    }


    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(adapterView.getContext(), DeviceActivity.class);
            intent.putExtra(DeviceActivity.EXTRA_DEVICE_ADDRESS, ((BluetoothDevice)adapterView.getItemAtPosition(i)).getAddress());

            mBtAdapter.cancelDiscovery();

            startActivity(intent);
            finish();
        }
    };

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d(TAG, "New device found");
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                mDevicesAdapter.add(device);
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d(TAG, "Discovery finished");
                if (mDevicesAdapter.getCount() == 0) {
                    Log.d(TAG, "No device found");
                    Toast.makeText(context, "No scanned device", Toast.LENGTH_SHORT);
                }
            }

            mDevicesAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBtAdapter != null) {
                mBtAdapter.cancelDiscovery();
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }

        this.unregisterReceiver(mReceiver);

        super.onDestroy();
    }
}
