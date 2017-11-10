package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.BluetoothListComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.ExerciseTypesComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.LoginComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.MainComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.SplashComponent;

/**
 * Created by Alex on 08/11/2017.
 */

@Module(subcomponents = {
        MainComponent.class,
        BluetoothListComponent.class,
        LoginComponent.class,
        ExerciseTypesComponent.class,
        SplashComponent.class
})
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
}
