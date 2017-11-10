package edu.berkeley.capstoneproject.capstoneprojectandroid.di;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.multibindings.IntoMap;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.ExerciseTypesComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.BluetoothListComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.LoginComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.MainComponent;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(BluetoothListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindBluetoothListActivity(BluetoothListComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivity(LoginComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(ExerciseTypesActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindExercisesActivity(ExerciseTypesComponent.Builder builder);
}
