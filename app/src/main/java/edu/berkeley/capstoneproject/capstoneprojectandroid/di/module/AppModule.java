package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref.IPreferencesHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref.PreferencesHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.PreferenceInfo;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.session.ISessionHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.session.SessionHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.AppConstants;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class AppModule {

    final CapstoneProjectAndroidApplication mApplication;

    public AppModule(CapstoneProjectAndroidApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    CapstoneProjectAndroidApplication provideApp() { return mApplication; }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager dataManager) {
        return dataManager;
    }


    @Provides
    @Singleton
    IApiHelper provideApiHelper(ApiHelper apiHelper) {
        return apiHelper;
    }


    @Provides
    @Singleton
    IBluetoothHelper provideBluetoothHelper(BluetoothHelper bluetoothHelper) {
        return bluetoothHelper;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREFERENCES_NAME;
    }

    @Provides
    @Singleton
    IPreferencesHelper providePreferencesHelper(PreferencesHelper preferencesHelper) {
        return preferencesHelper;
    }

    @Provides
    @Singleton
    ISessionHelper provideSessionHelper(SessionHelper sessionHelper) {
        return sessionHelper;
    }
}
