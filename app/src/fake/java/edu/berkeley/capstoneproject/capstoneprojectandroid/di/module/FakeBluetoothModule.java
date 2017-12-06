package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.hardware.SensorManager;

import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.mockrxandroidble.RxBleClientMock;

import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.DeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.MeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;

/**
 * Created by Alex on 05/12/2017.
 */

@Module(includes = FakeAppModule.class)
public class FakeBluetoothModule {

    @Provides
    @Singleton
    List<BluetoothGattDescriptor> provideDescriptors() {
        return new RxBleClientMock.DescriptorsBuilder()
                .addDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"), new byte[]{(byte) 0x00})
                .build();
    }

    @Provides
    @Singleton
    List<BluetoothGattCharacteristic> provideCharacteristics(List<BluetoothGattDescriptor> descriptors) {
        return new RxBleClientMock.CharacteristicsBuilder()
                .addCharacteristic(BluetoothConstants.UUID_CHARACTERISTIC_ENCODER, new byte[] {(byte) 0x00}, descriptors)
                .build();
    }

    @Provides
    @Singleton
    RxBleDevice provideRxBleDevice(List<BluetoothGattCharacteristic> characteristicList) {
        return new RxBleClientMock.DeviceBuilder()
                .deviceName("Fake device")
                .deviceMacAddress("00:00:00:00:00:00")
                .scanRecord(new byte[] {(byte) 0x00})
                .rssi(1)
                .addService(
                        BluetoothConstants.UUID_SERVICE,
                        characteristicList
                ).build();
    }


    @Provides
    @Singleton
    RxBleClient provideRxBleClient(RxBleDevice device) {
        return new RxBleClientMock.Builder()
                .addDevice(device)
                .build();
    }

    @Provides
    @Singleton
    SensorManager provideSensorManager(Context context) {
        return (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
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
