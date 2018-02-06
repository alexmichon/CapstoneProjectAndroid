package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseBuilderManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ITrainingManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IHistoryManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.IMeasurementManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;

/**
 * Created by Alex on 08/11/2017.
 */

@Singleton
@Component(modules = {AppModule.class}, dependencies = {BluetoothComponent.class, NetworkComponent.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(CapstoneProjectAndroidApplication app);
        Builder appModule(AppModule appModule);
        Builder bluetoothComponent(BluetoothComponent bluetoothComponent);
        Builder networkComponent(NetworkComponent networkComponent);
        AppComponent build();
    }

    void inject(CapstoneProjectAndroidApplication app);

    IAuthManager authManager();
    IExerciseTypeManager exerciseTypeManager();
    ITrainingManager exerciseManager();
    IExerciseBuilderManager exerciseBuilderManager();
    IMeasurementManager measurementManager();
    IBluetoothManager bluetoothManager();
    IHistoryManager historyManager();
}
