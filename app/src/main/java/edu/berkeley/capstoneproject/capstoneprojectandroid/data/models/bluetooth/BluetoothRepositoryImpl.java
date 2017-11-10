package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth;

import android.bluetooth.*;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.Specification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Cancellable;

/**
 * Created by Alex on 08/11/2017.
 */

@Singleton
public class BluetoothRepositoryImpl implements BluetoothRepository {

    private static final String TAG = BluetoothRepositoryImpl.class.getSimpleName();

    BluetoothLeAdapter mAdapter;

    @Inject
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
    public void create(BluetoothDevice item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Iterable<BluetoothDevice> items) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(BluetoothDevice item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(BluetoothDevice item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Specification specification) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<BluetoothDevice> query() {
        return null;
    }

    @Override
    public Observable<BluetoothDevice> query(Specification specification) {
        return null;
    }
}
