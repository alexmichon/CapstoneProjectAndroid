package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BluetoothManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.IMeasurementManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.MeasurementManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.AuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref.IPreferencesHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref.PreferencesHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.PreferenceInfo;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.session.ISessionHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.session.SessionHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.AppConstants;

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
    @ApplicationContext
    Context provideContext() {
        return mApplication;
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
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREFERENCES_NAME;
    }

    @Provides
    @Singleton
    IPreferencesHelper providePreferencesHelper(PreferencesHelper preferencesHelper) {
        return preferencesHelper;
    }

    @Provides
    @Singleton
    ISessionHelper provideSessionHelper(SessionHelper sessionHelper) {
        return sessionHelper;
    }

    @Provides
    @Singleton
    IAuthManager provideAuthManager(AuthManager authManager) {
        return authManager;
    }

    @Provides
    @Singleton
    IExerciseTypeManager provideExerciseTypeManager(ExerciseTypeManager exerciseTypeManager) {
        return exerciseTypeManager;
    }

    @Provides
    @Singleton
    IExerciseManager provideExerciseManager(ExerciseManager exerciseManager) {
        return exerciseManager;
    }

    @Provides
    @Singleton
    IMeasurementManager provideMeasurementManager(MeasurementManager measurementManager) {
        return measurementManager;
    }

    @Provides
    @Singleton
    IBluetoothManager provideBluetoothManager(BluetoothManager bluetoothManager) {
        return bluetoothManager;
    }
}
