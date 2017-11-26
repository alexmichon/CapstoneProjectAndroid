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
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.connection.ConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.connection.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.device.DeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.device.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.exercise.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.exercise.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.measurement.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.measurement.MeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IAuthService;

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
    IAuthService provideAuthService(AuthService service) {
        return service;
    }

    @Provides
    @Singleton
    edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IExerciseService provideExerciseService(
            edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.ExerciseService service
    ) {
        return service;
    }






    @Provides
    @Singleton
    IBluetoothHelper provideBluetoothHelper(BluetoothHelper bluetoothHelper) {
        return bluetoothHelper;
    }

    @Provides
    @Singleton
    IDeviceService provideDeviceService(DeviceService service) {
        return service;
    }

    @Provides
    @Singleton
    IConnectionService provideConnectionService(ConnectionService service) {
        return service;
    }

    @Provides
    @Singleton
    IExerciseService provideBluetoothExerciseService(
            ExerciseService service
    ) {
        return service;
    }

    @Provides
    @Singleton
    IMeasurementService provideSensorService(MeasurementService service) {
        return service;
    }

    @Provides
    RxBleClient provideRxBleClient(Context context) {
        return RxBleClient.create(context);
    }
}
