package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.hardware.SensorManager;

import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.mockrxandroidble.RxBleClientMock;

import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.DeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.MeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;

/**
 * Created by Alex on 05/12/2017.
 */

@Module
public class FakeTestBluetoothModule {

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
