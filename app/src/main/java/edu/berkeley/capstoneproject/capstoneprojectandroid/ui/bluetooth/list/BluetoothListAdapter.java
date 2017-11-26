package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.polidea.rxandroidble.RxBleDevice;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListAdapter extends ArrayAdapter<Rx2BleDevice> {

    private static final String TAG = BluetoothListAdapter.class.getSimpleName();

    private LayoutInflater mInflater;

    public BluetoothListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
    }


    private class Holder {
        TextView mTextName;
        TextView mTextAddress;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BluetoothListAdapter.Holder holder = new BluetoothListAdapter.Holder();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.bluetooth_device, parent);
            holder.mTextName = (TextView) convertView.findViewById(R.id.text_bluetooth_name);
            holder.mTextAddress = (TextView) convertView.findViewById(R.id.text_bluetooth_address);

            convertView.setTag(holder);
        }
        else {
            holder = (BluetoothListAdapter.Holder) convertView.getTag();
        }

        Rx2BleDevice bluetoothDevice = getItem(position);
        if (bluetoothDevice != null) {
            holder.mTextName.setText(bluetoothDevice.getName());
            holder.mTextAddress.setText(bluetoothDevice.getMacAddress());
        }

        return convertView;
    }
}
