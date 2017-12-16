package edu.berkeley.capstoneproject.capstoneprojectandroid.data;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref.IPreferencesHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.session.ISessionHelper;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public interface IDataManager {

    IApiHelper getApiHelper();
    IBluetoothHelper getBluetoothHelper();
    IPreferencesHelper getPreferencesHelper();
    ISessionHelper getSessionHelper();
}
