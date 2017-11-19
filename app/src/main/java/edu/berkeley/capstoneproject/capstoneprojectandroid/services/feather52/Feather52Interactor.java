package edu.berkeley.capstoneproject.capstoneprojectandroid.services.feather52;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import io.reactivex.Observable;

/**
 * Created by Alex on 11/11/2017.
 */

public class Feather52Interactor extends BaseInteractor implements Feather52Contract.Interactor {

    //private final Feather52Contract.Service mService;

    @Inject
    public Feather52Interactor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<Rx2BleConnection> doConnect(boolean autoconnect) {
        //return mService.connect(autoconnect);
        return null;
    }
}
