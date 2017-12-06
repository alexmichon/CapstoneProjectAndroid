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

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.FakeApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;

/**
 * Created by Alex on 28/11/2017.
 */

@Module
public class FakeAppModule extends AppModule {

    public FakeAppModule(FakeApplication application) {
        super(application);
    }

}
