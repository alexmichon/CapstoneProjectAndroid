package edu.berkeley.capstoneproject.capstoneprojectandroid.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivityComponent;

/**
 * Created by Alex on 08/11/2017.
 */

@Module(subcomponents = {MainActivityComponent.class, BluetoothListActivityComponent.class, LoginActivityComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
