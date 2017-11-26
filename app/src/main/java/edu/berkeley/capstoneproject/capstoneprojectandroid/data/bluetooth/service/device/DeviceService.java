package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.device;

import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanResult;
import com.polidea.rxandroidble.scan.ScanSettings;

import java.util.Set;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.base.BaseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 26/11/2017.
 */

public class DeviceService extends BaseService implements IDeviceService {

    Rx2BleClient mRxBleClient;

    @Inject
    public DeviceService(Rx2BleClient client) {
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
}
