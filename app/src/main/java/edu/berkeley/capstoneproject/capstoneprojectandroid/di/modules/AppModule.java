package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.BluetoothListComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.ExercisesComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.LoginComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.MainComponent;

/**
 * Created by Alex on 08/11/2017.
 */

@Module(subcomponents = {
        MainComponent.class,
        BluetoothListComponent.class,
        LoginComponent.class,
        ExercisesComponent.class
})
public class AppModule {

    final CapstoneProjectAndroidApplication mApplication;

    public AppModule(CapstoneProjectAndroidApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    CapstoneProjectAndroidApplication provideApp() { return mApplication; }
}
