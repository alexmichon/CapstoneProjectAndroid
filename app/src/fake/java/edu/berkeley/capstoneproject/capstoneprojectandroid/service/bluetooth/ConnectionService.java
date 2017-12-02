package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 28/11/2017.
 */

public class ConnectionService extends BaseService implements IConnectionService {

    @Inject
    public ConnectionService() {

    }

    @Override
    public Single<Rx2BleConnection> connect(Rx2BleDevice device, boolean autoconnect) {
        return Single.just(new Rx2BleConnection(null));
    }

    @Override
    public void disconnect() {

    }

    @Override
    public Rx2BleDevice.ConnectionState getConnectionState() {
        return Rx2BleDevice.ConnectionState.CONNECTED;
    }

    @Override
    public Observable<Rx2BleDevice.ConnectionState> observeConnectionStateChange() {
        return Observable.never();
    }

    @Override
    public Completable validateDevice() {
        return Completable.complete();
    }
}
