package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble;

import android.bluetooth.BluetoothGattService;

import com.polidea.rxandroidble.RxBleDeviceServices;

import java.util.UUID;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.convert.RxObservableConverter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Alex on 17/11/2017.
 */

public class Rx2BleDeviceServices {

    private final RxBleDeviceServices mDeviceServices;

    public Rx2BleDeviceServices(RxBleDeviceServices services) {
        mDeviceServices = services;
    }

    public Observable<BluetoothGattService> getService(UUID uuid) {
        final rx.Observable<BluetoothGattService> observable = mDeviceServices.getService(uuid);
        return RxObservableConverter.convert(observable);
    }

}
