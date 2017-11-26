package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;

/**
 * Created by Alex on 24/11/2017.
 */

@Module
public class TestAppModule {

    private final Application mApplication;

    public TestAppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    IApiHelper provideApiHelper(ApiHelper apiHelper) {
        return apiHelper;
    }

    @Provides
    @Singleton
    ApiHeader provideApiHeader() {
        return new ApiHeader();
    }

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
    IBluetoothHelper provideBluetoothHelper(BluetoothHelper bluetoothHelper) {
        return bluetoothHelper;
    }

    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager dataManager) {
        return dataManager;
    }
}
