package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import org.mockito.Mockito;

import dagger.Module;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExerciseContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExerciseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExercisePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type.ExerciseTypesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type.ExerciseTypesFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type.ExerciseTypesInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type.ExerciseTypesPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register.RegisterActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register.RegisterContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register.RegisterInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register.RegisterPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 25/11/2017.
 */

public class TestActivityModule extends ActivityModule {

    private final boolean mMockMode;

    public TestActivityModule(AppCompatActivity activity, boolean mockMode) {
        super(activity);
        mMockMode = mockMode;
    }

    @Override
    LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> provideLoginPresenter(LoginPresenter<LoginContract.View, LoginContract.Interactor> presenter) {
        if (mMockMode) {
            return Mockito.mock(LoginContract.Presenter.class);
        }
        return super.provideLoginPresenter(presenter);
    }
}
