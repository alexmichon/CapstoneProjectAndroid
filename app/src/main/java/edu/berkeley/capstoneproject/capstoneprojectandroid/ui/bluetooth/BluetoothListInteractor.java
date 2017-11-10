package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothDevice;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.IBluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class BluetoothListInteractor extends BaseInteractor implements BluetoothListContract.Interactor {

    private final IBluetoothRepository mBluetoothRepository;

    @Inject
    public BluetoothListInteractor(IBluetoothRepository repository) {
        mBluetoothRepository = repository;
    }

    @Override
    public Observable<BluetoothDevice> doDiscovery() {
        return mBluetoothRepository.getScannedDevices();
    }

    @Override
    public Observable<BluetoothDevice> doLoadPairedDevices() {
        return mBluetoothRepository.getPairedDevices();
    }
}
