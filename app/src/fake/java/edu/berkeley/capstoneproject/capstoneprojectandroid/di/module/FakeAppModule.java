package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.hardware.SensorManager;

import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.RxBleDeviceServices;
import com.polidea.rxandroidble.mockrxandroidble.RxBleClientMock;
import com.polidea.rxandroidble.mockrxandroidble.RxBleDeviceMock;

import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.FakeApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.DeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.MeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;

/**
 * Created by Alex on 28/11/2017.
 */

@Module
public class FakeAppModule extends AppModule {

    public FakeAppModule(FakeApplication application) {
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
    IMeasurementService provideMeasurementService(MeasurementService service) {
        return service;
    }
}
