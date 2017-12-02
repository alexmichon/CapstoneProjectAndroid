package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;

import com.polidea.rxandroidble.RxBleClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.DeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.MeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;

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
}
