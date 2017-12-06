package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;

import com.polidea.rxandroidble.RxBleClient;

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
}
