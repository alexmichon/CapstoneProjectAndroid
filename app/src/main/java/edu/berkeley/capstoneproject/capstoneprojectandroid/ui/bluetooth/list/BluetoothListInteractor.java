package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 10/11/2017.
 */

public class BluetoothListInteractor extends BaseInteractor implements BluetoothListContract.Interactor {;

    @Inject
    public BluetoothListInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<Rx2BleDevice> doDiscovery() {
        return getDataManager().getBluetoothHelper().getDeviceService().getScannedDevices();
    }

    @Override
    public Observable<Rx2BleDevice> doLoadPairedDevices() {
        return getDataManager().getBluetoothHelper().getDeviceService().getPairedDevices();
    }
}
