package edu.berkeley.capstoneproject.capstoneprojectandroid.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class DataManager implements IDataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private final IApiHelper mApiHelper;
    private final IBluetoothHelper mBluetoothHelper;

    @Inject
    public DataManager(IApiHelper apiHelper, IBluetoothHelper bluetoothHelper) {
        mApiHelper = apiHelper;
        mBluetoothHelper = bluetoothHelper;
    }

    @Override
    public IApiHelper getApiHelper() {
        return mApiHelper;
    }

    @Override
    public IBluetoothHelper getBluetoothHelper() {
        return mBluetoothHelper;
    }
}
