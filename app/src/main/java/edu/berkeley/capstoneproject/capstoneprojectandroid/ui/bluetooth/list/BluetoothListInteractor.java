package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.IBluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class BluetoothListInteractor extends BaseInteractor implements BluetoothListContract.Interactor {

    private final IBluetoothRepository mBluetoothRepository;

    @Inject
    public BluetoothListInteractor(IDataManager dataManager, IBluetoothRepository repository) {
        super(dataManager);
        mBluetoothRepository = repository;
    }

    @Override
    public Observable<Rx2BleDevice> doDiscovery() {
        return mBluetoothRepository.getScannedDevices();
    }

    @Override
    public Observable<Rx2BleDevice> doLoadPairedDevices() {
        return mBluetoothRepository.getPairedDevices();
    }

    @Override
    public void doSelectDevice(Rx2BleDevice device) {
        getDataManager().getBluetoothHelper().setDevice(device);
    }

    @Override
    public Observable<Rx2BleDevice.ConnectionState> doConnectDevice() {
        return getDataManager().getBluetoothHelper().connect(false);
    }

    @Override
    public Completable doValidateDevice() {
        return getDataManager().getBluetoothHelper().validateDevice();
    }
}
