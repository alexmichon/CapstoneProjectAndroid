package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import com.polidea.rxandroidble.RxBleScanResult;
import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanResult;
import com.polidea.rxandroidble.scan.ScanSettings;

import java.util.UUID;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 28/11/2017.
 */

public class DeviceService extends BaseService implements IDeviceService {

    Rx2BleClient mRxBleClient;

    @Inject
    public DeviceService(Rx2BleClient client) {
        mRxBleClient = client;
    }


    @Override
    public Observable<Rx2BleDevice> getPairedDevices() {
        return Observable.fromIterable(mRxBleClient.getBondedDevices());
    }

    @Override
    public Observable<Rx2BleDevice> getScannedDevices() {
        return mRxBleClient.scanBleDevices(BluetoothConstants.UUID_SERVICE)
                .map(new Function<RxBleScanResult, Rx2BleDevice>() {
                        @Override
                        public Rx2BleDevice apply(@NonNull RxBleScanResult scanResult) throws Exception {
                            return new Rx2BleDevice(scanResult.getBleDevice());
                        }
                });
    }
}
