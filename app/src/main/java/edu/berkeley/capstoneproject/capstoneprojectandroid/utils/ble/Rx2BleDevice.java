package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble;

/**
 * Created by Alex on 17/11/2017.
 */

import android.bluetooth.BluetoothDevice;

import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleDevice;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.convert.RxObservableConverter;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/** Wrapper of RxBleDevice class using RxJava2 */
public class Rx2BleDevice {

    private static final String TAG = Rx2BleDevice.class.getSimpleName();

    public enum ConnectionState {
        CONNECTING("CONNECTING"), CONNECTED("CONNECTED"), DISCONNECTED("DISCONNECTED"), DISCONNECTING("DISCONNECTING");

        private final String description;

        ConnectionState(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "RxBleConnectionState{" + description + '}';
        }
    }

    private final RxBleDevice mDevice;

    public Rx2BleDevice(RxBleDevice device) {
        mDevice = device;
    }

    public Observable<Rx2BleConnection> establishConnection(boolean autoconnect) {
        final rx.Observable<RxBleConnection> connectionObservable = mDevice.establishConnection(autoconnect);
        return RxObservableConverter.convert(connectionObservable).map(new Function<RxBleConnection, Rx2BleConnection>() {
            @Override
            public Rx2BleConnection apply(@NonNull RxBleConnection connection) throws Exception {
                return new Rx2BleConnection(connection);
            }
        });
    }

    public Observable<RxBleConnection.RxBleConnectionState> observeConnectionStateChange() {
        final rx.Observable<RxBleConnection.RxBleConnectionState> observable = mDevice.observeConnectionStateChanges();
        return RxObservableConverter.convert(observable);
    }

    public BluetoothDevice getBluetoothDevice() {
        return mDevice.getBluetoothDevice();
    }

    public String getName() {
        return mDevice.getName();
    }

    public String getMacAddress() {
        return mDevice.getMacAddress();
    }

    public ConnectionState getConnectionState() {
        RxBleConnection.RxBleConnectionState state = mDevice.getConnectionState();
        switch (state) {
            case CONNECTED:
                return ConnectionState.CONNECTED;
            case CONNECTING:
                return ConnectionState.CONNECTING;
            case DISCONNECTED:
                return ConnectionState.DISCONNECTED;
            case DISCONNECTING:
                return ConnectionState.DISCONNECTING;
        }

        return ConnectionState.DISCONNECTED;
    }
}
