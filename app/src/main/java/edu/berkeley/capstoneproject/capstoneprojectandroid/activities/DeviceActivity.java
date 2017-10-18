package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;

public class DeviceActivity extends AppCompatActivity {

    private static final String TAG = "DeviceActivity";

    public static final String EXTRA_DEVICE_ADDRESS = "device_address";

    private BluetoothDevice mBluetoothDevice;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        String address = getIntent().getStringExtra(EXTRA_DEVICE_ADDRESS);
        mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(address);

        setTitle(mBluetoothDevice.getName());
    }
}
