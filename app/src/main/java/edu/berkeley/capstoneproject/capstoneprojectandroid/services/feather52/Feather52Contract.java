package edu.berkeley.capstoneproject.capstoneprojectandroid.services.feather52;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public interface Feather52Contract {

    interface Service {
        Observable<Rx2BleConnection> connect(boolean autoconnect);
    }

    interface Interactor {
        Observable<Rx2BleConnection> doConnect(boolean autoconnect);
    }
}
