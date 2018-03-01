package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 26/11/2017.
 */

public interface IConnectionService extends IBaseService {

    Single<Rx2BleConnection> connect(Rx2BleDevice device, boolean autoconnect);

    void disconnect();

    Completable validateDevice(Rx2BleConnection connection);
}
