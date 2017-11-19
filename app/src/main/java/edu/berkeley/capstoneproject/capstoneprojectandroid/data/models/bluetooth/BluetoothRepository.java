package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth;

import android.bluetooth.*;

import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.internal.operations.ScanOperation;
import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanResult;
import com.polidea.rxandroidble.scan.ScanSettings;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.ISpecification;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Function;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Alex on 08/11/2017.
 */

@Singleton
public class BluetoothRepository implements IBluetoothRepository {

    private static final String TAG = BluetoothRepository.class.getSimpleName();

    Rx2BleClient mRxBleClient;

    @Inject
    public BluetoothRepository(Rx2BleClient client) {
        mRxBleClient = client;
    }

    @Override
    public Observable<Rx2BleDevice> getPairedDevices() {
        return Observable.just(mRxBleClient.getBondedDevices()).flatMap(new Function<Set<Rx2BleDevice>, ObservableSource<Rx2BleDevice>>() {
            @Override
            public ObservableSource<Rx2BleDevice> apply(@NonNull Set<Rx2BleDevice> rxBleDevices) throws Exception {
                return Observable.fromIterable(rxBleDevices);
            }
        });
    }

    @Override
    public Observable<Rx2BleDevice> getScannedDevices() {
        return mRxBleClient.scanBleDevices(
                new ScanSettings.Builder()
                        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                        .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                        .build(),
                new ScanFilter.Builder()
                        // add custom filters if needed
                        .build()
        ).map(new Function<ScanResult, Rx2BleDevice>() {
            @Override
            public Rx2BleDevice apply(@NonNull ScanResult scanResult) throws Exception {
                return new Rx2BleDevice(scanResult.getBleDevice());
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
    public void delete(ISpecification specification) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<BluetoothDevice> query() {
        return null;
    }

    @Override
    public Observable<BluetoothDevice> query(ISpecification specification) {
        return null;
    }
}
