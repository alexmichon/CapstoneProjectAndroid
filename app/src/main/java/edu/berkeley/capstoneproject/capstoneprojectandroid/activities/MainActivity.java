package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.adapters.BluetoothDeviceAdapter;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private static final int REQUEST_ENABLE_BT = 1;

    private final BluetoothManager mBtManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
    private BluetoothAdapter mBtAdapter;

    private Button mScanButton;
    private BluetoothDeviceAdapter mPairedDevicesAdapter, mScannedDevicesAdapter;
    private ListView mPairedListView, mScannedListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Bluetooth LE required !", Toast.LENGTH_SHORT).show();
            finish();
        }

        initViews();

        mBtAdapter = mBtManager.getAdapter();
        if (mBtAdapter == null || !mBtAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        else {
            populatePairedDevices();
        }

        registerBtReceiver();
    }

    private void initViews() {
        mPairedDevicesAdapter = new BluetoothDeviceAdapter(this, R.layout.bluetooth_device);
        mScannedDevicesAdapter = new BluetoothDeviceAdapter(this, R.layout.bluetooth_device);

        mPairedListView = (ListView) findViewById(R.id.list_bluetooth_paired_devices);
        mPairedListView.setAdapter(mPairedDevicesAdapter);
        mPairedListView.setOnItemClickListener(mDeviceClickListener);

        mScannedListView = (ListView) findViewById(R.id.list_bluetooth_scanned_devices);
        mScannedListView.setAdapter(mScannedDevicesAdapter);
        mScannedListView.setOnItemClickListener(mDeviceClickListener);

        mScanButton = (Button) findViewById(R.id.button_bluetooth_scan);
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Start Discovery");

                // If we're already discovering, stop it
                if (mBtAdapter.isDiscovering()) {
                    mBtAdapter.cancelDiscovery();
                }

                mScannedDevicesAdapter.clear();
                mScannedDevicesAdapter.notifyDataSetChanged();

                mBtAdapter.startDiscovery();
                view.setVisibility(View.GONE);
            }
        });

        enableScanButton();
    }


    private void enableScanButton() {
        mScanButton.setEnabled(mBtAdapter != null && mBtAdapter.isEnabled());
    }


    private void registerBtReceiver() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        this.registerReceiver(mReceiver, filter);
    }


    private void populatePairedDevices() {
        Log.d(TAG, "Populating paired devices");

        if (mBtAdapter == null || !mBtAdapter.isEnabled()) {
            mPairedDevicesAdapter.clear();
        }
        else {
            Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesAdapter.addAll(pairedDevices);
            }

            if (pairedDevices.size() == 0) {
                Toast.makeText(this, "No paired device", Toast.LENGTH_SHORT).show();
            }
        }

        mPairedDevicesAdapter.notifyDataSetChanged();
    }

    private void addScannedDevice(BluetoothDevice device) {
        if (!mBtAdapter.getBondedDevices().contains(device)) {
            mScannedDevicesAdapter.add(device);
            mScannedDevicesAdapter.notifyDataSetChanged();
        }
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

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                Log.d(TAG, "Bluetooth state changed");
                populatePairedDevices();
                enableScanButton();
            }
            // When discovery finds a device
            else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d(TAG, "New device found");
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                addScannedDevice(device);
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d(TAG, "Discovery finished");
                if (mScannedDevicesAdapter.getCount() == 0) {
                    Log.d(TAG, "No device found");
                    Toast.makeText(context, "No device found", Toast.LENGTH_SHORT).show();
                    mScanButton.setVisibility(View.VISIBLE);
                }
            }


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
