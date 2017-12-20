package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingInteractor extends BaseInteractor implements TrainingContract.Interactor {

    @Inject
    public TrainingInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void doSelectDevice(Rx2BleDevice device) {
        getDataManager().getBluetoothHelper().setDevice(device);
    }

    @Override
    public Single<Rx2BleConnection> doConnect(final Rx2BleDevice device) {
        return getDataManager().getBluetoothHelper().getConnectionService().connect(device, false)
                .map(new Function<Rx2BleConnection, Rx2BleConnection>() {
                    @Override
                    public Rx2BleConnection apply(@NonNull Rx2BleConnection connection) throws Exception {
                        getDataManager().getBluetoothHelper().setConnection(connection);
                        getDataManager().getBluetoothHelper().setDevice(device);
                        return connection;
                    }
                });
    }

    @Override
    public Completable doValidateDevice() {
        return getDataManager().getBluetoothHelper().getConnectionService().validateDevice();
    }

    @Override
    public void doDisconnect() {
        getDataManager().getBluetoothHelper().getConnectionService().disconnect();
    }

    @Override
    public Completable doClearExerciseSession() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().resetExerciseCreator();
    }
}
