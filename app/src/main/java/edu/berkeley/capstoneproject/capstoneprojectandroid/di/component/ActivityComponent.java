package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExerciseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type.ExerciseTypesFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register.RegisterActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;

/**
 * Created by Alex on 18/11/2017.
 */

@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(BluetoothListFragment fragment);
    void inject(LoginActivity activity);
    void inject(RegisterActivity activity);
    void inject(SplashActivity activity);
    void inject(TrainingActivity activity);
    void inject(ExerciseFragment fragment);
    void inject(ExerciseTypesFragment fragment);
}
