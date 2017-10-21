package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.SampleGattAttributes;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.BluetoothLeService;

public class DeviceLeActivity extends AppCompatActivity {

    private static final String TAG = "DeviceLeActivity";

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private String mDeviceName;
    private String mDeviceAddress;

    private boolean mConnected = false;

    private BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<>();
    private BluetoothGattCharacteristic mNotifyCharacteristic;

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    private ExpandableListView mGattServicesList;
    private ExpandableListAdapter mGattServicesAdapter;
    private TextView mDataView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_le);

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        setTitle(mDeviceName);

        mGattServicesList = (ExpandableListView) findViewById(R.id.device_le_gatt_services_list);
        mGattServicesList.setOnChildClickListener(mServicesClickListener);

        mDataView = (TextView) findViewById(R.id.device_le_data_view);

        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.device_le_gatt_services, menu);
        menu.findItem(R.id.device_le_menu_connect).setVisible(!mConnected);
        menu.findItem(R.id.device_le_menu_disconnect).setVisible(mConnected);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.device_le_menu_connect:
                mBluetoothLeService.connect(mDeviceAddress);
                return true;
            case R.id.device_le_menu_disconnect:
                mBluetoothLeService.disconnect();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearUI() {
        mGattServicesList.setAdapter((SimpleExpandableListAdapter) null);
        mDataView.setText("");
    }

    private void displayData(String data) {
        if (data != null) {
            mDataView.setText(data);
        }
    }

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) {
            return;
        }

        String uuid = null;
        List<HashMap<String, String>> gattServiceData = new ArrayList<>();
        List<ArrayList<HashMap<String,String>>> gattCharacteristicData = new ArrayList<>();

        mGattCharacteristics = new ArrayList<>();

        for (BluetoothGattService gattService: gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<>();
            uuid = gattService.getUuid().toString();

            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, "Unknown")
            );
            currentServiceData.put(
                    LIST_UUID, uuid
            );
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String,String>> gattCharacteristicGroupData = new ArrayList<>();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<>();

            for (BluetoothGattCharacteristic characteristic: gattCharacteristics) {
                charas.add(characteristic);
                HashMap<String,String> currentCharaData = new HashMap<>();
                uuid = characteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, "Unknown")
                );
                currentCharaData.put(
                        LIST_UUID, uuid
                );
                gattCharacteristicGroupData.add(currentCharaData);
            }

            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

        mGattServicesAdapter = new SimpleExpandableListAdapter(
                this,
                gattServiceData,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {LIST_NAME, LIST_UUID},
                new int[] {android.R.id.text1, android.R.id.text2},
                gattCharacteristicData,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {LIST_NAME, LIST_UUID},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        mGattServicesList.setAdapter(mGattServicesAdapter);
    }


    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }



    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "Service connected");
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) iBinder).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }

            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "Service disconnected");
            mBluetoothLeService = null;
        }
    };



    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                invalidateOptionsMenu();
            }
            else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                invalidateOptionsMenu();
                clearUI();
            }
            else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            }
            else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };


    private final ExpandableListView.OnChildClickListener mServicesClickListener = new ExpandableListView.OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
            if (mGattCharacteristics != null) {
                final BluetoothGattCharacteristic characteristic = mGattCharacteristics.get(i).get(i1);
                final int charaProp = characteristic.getProperties();
                if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                    if (mNotifyCharacteristic != null) {
                        mBluetoothLeService.setCharacteristicNotification(mNotifyCharacteristic, false);
                        mNotifyCharacteristic = null;
                    }
                    mBluetoothLeService.readCharacteristic(characteristic);
                }
                if ((charaProp |BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                    mNotifyCharacteristic = characteristic;
                    mBluetoothLeService.setCharacteristicNotification(characteristic, true);
                }
                return true;
            }
            return false;
        }
    };
}
