package edu.berkeley.capstoneproject.capstoneprojectandroid.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref.IPreferencesHelper;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class DataManager implements IDataManager {

    private final IApiHelper mApiHelper;
    private final IBluetoothHelper mBluetoothHelper;
    private final IPreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(IApiHelper apiHelper, IBluetoothHelper bluetoothHelper, IPreferencesHelper preferencesHelper) {
        mApiHelper = apiHelper;
        mBluetoothHelper = bluetoothHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public IApiHelper getApiHelper() {
        return mApiHelper;
    }

    @Override
    public IBluetoothHelper getBluetoothHelper() {
        return mBluetoothHelper;
    }

    @Override
    public IPreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }
}
