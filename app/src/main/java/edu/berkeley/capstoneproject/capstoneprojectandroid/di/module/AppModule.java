package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IExerciseService;

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

    /*
    @Provides
    @Singleton
    IFeather52Service provideFeather52Service() {
        return new Feather52Service2();
    }
    */

    @Provides
    @Singleton
    IAuthService provideAuthService(AuthService service) {
        return service;
    }

    @Provides
    @Singleton
    IExerciseService provideExerciseService(ExerciseService service) {
        return service;
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

    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager dataManager) {
        return dataManager;
    }
}
