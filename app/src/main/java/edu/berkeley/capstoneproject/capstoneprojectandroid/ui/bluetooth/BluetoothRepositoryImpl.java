package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.*;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.berkeley.capstoneproject.capstoneprojectandroid.Specification;
import edu.berkeley.capstoneproject.capstoneprojectandroid.activities.MainActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 08/11/2017.
 */

@TargetApi(21)
public class BluetoothRepositoryImpl implements BluetoothRepository {

    private static final String TAG = BluetoothRepositoryImpl.class.getSimpleName();

    private BluetoothLeAdapter mAdapter;

    public BluetoothRepositoryImpl(BluetoothLeAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public Observable<BluetoothDevice> getPairedDevices() {
        return Observable.create(new ObservableOnSubscribe<BluetoothDevice>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<BluetoothDevice> e) throws Exception {
                List<BluetoothDevice> devices = mAdapter.getPairedDevices();
                for (BluetoothDevice device: devices) {
                    e.onNext(device);
                }
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<BluetoothDevice> getScannedDevices() {
        return Observable.create(new ObservableOnSubscribe<BluetoothDevice>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<BluetoothDevice> e) throws Exception {
                mAdapter.setScanListener(new BluetoothLeAdapter.BluetoothScanListener() {
                    @Override
                    public void onNewDevice(BluetoothDevice device) {
                        e.onNext(device);
                    }

                    @Override
                    public void onComplete() {
                        e.onComplete();
                    }

                    @Override
                    public void onError(Throwable t) {
                        e.onError(t);
                    }
                });
                mAdapter.startDiscovery();

                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        mAdapter.cancelDiscovery();
                    }
                });
            }
        });
    }

    @Override
    public void add(BluetoothDevice item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Iterable<BluetoothDevice> items) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(BluetoothDevice item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(BluetoothDevice item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BluetoothDevice> query(Specification specification) {
        return null;
    }
}
