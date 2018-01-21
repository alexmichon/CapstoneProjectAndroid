package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;

/**
 * Created by Alex on 05/12/2017.
 */

@Module
public class FullTestBluetoothModule extends FullBluetoothModule {

    @Provides
    @Singleton
    IDeviceService provideDeviceService() {
        return Mockito.mock(IDeviceService.class);
    }

    @Provides
    @Singleton
    IConnectionService provideConnectionService() {
        return Mockito.mock(IConnectionService.class);
    }

    @Provides
    @Singleton
    IExerciseService provideExerciseService() {
        return Mockito.mock(IExerciseService.class);
    }

    @Provides
    @Singleton
    IMeasurementService provideSensorService() {
        return Mockito.mock(IMeasurementService.class);
    }
}
