package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.FullApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.DeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.MeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;

/**
 * Created by Alex on 27/11/2017.
 */

@Module
public class FullAppModule extends AppModule {
    public FullAppModule(FullApplication application) {
        super(application);
    }

    @Provides
    @Singleton
    IAuthService provideAuthService(AuthService service) {
        return service;
    }

    @Provides
    @Singleton
    edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService
    provideExerciseService(edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.ExerciseService service) {
        return service;
    }

    @Provides
    @Singleton
    RxBleClient provideRxBleClient(Context context) {
        return RxBleClient.create(context);
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
    edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService
    provideBluetoothExerciseService(edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService service) {
        return service;
    }

    @Provides
    @Singleton
    IMeasurementService provideSensorService(MeasurementService service) {
        return service;
    }
}
