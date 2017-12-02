package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble;

import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.RxBleScanResult;
import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanResult;
import com.polidea.rxandroidble.scan.ScanSettings;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.convert.RxObservableConverter;
import io.reactivex.Observable;

/**
 * Created by Alex on 17/11/2017.
 */

public class Rx2BleClient {

    RxBleClient mClient;

    @Inject
    public Rx2BleClient(RxBleClient client) {
        mClient = client;
    }

    public Set<Rx2BleDevice> getBondedDevices() {
        Set<RxBleDevice> devices = mClient.getBondedDevices();

        Set<Rx2BleDevice> devices2 = new HashSet<>();
        for (RxBleDevice d: devices) {
            devices2.add(new Rx2BleDevice(d));
        }

        return devices2;
    }

    public Rx2BleDevice getBleDevice(String macAddress) {
        RxBleDevice device = mClient.getBleDevice(macAddress);
        if (device == null) {
            return null;
        }
        return new Rx2BleDevice(device);
    }

    public Observable<ScanResult> scanBleDevices(ScanSettings scanSettings, ScanFilter... scanFilters) {
        rx.Observable<ScanResult> observable = mClient.scanBleDevices(scanSettings, scanFilters);
        return RxObservableConverter.convert(observable);
    }

    public Observable<RxBleScanResult> scanBleDevices(UUID... filterServiceUUIDs) {
        rx.Observable<RxBleScanResult> observable = mClient.scanBleDevices(filterServiceUUIDs);
        return RxObservableConverter.convert(observable);
    }
}
