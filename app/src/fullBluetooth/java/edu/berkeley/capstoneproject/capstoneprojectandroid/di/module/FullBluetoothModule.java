package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import com.polidea.rxandroidble.RxBleClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.DeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.MeasurementService;

/**
 * Created by Alex on 05/12/2017.
 */

@Module(includes = AppModule.class)
public class FullBluetoothModule {

    @Provides
    @Singleton
    BluetoothAdapter provideBluetoothAdaoter() {
        return BluetoothAdapter.getDefaultAdapter();
    }


    @Provides
    @Singleton
    RxBleClient provideRxBleClient(@ApplicationContext Context context) {
        return RxBleClient.create(context);
    }

    @Provides
    @Singleton
    IDeviceService provideDeviceService(DeviceService deviceService) { return deviceService; }

    @Provides
    @Singleton
    IConnectionService provideConnectionService(ConnectionService connectionService) { return connectionService; }

    @Provides
    @Singleton
    IExerciseService provideExerciseService(ExerciseService exerciseService) { return exerciseService; }

    @Provides
    @Singleton
    IMeasurementService provideMeasurementService(MeasurementService measurementService) { return measurementService; }
}
