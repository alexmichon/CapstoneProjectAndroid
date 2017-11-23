package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import com.polidea.rxandroidble.RxBleClient;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.BluetoothLe18Adapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.BluetoothLe21Adapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.BluetoothLeAdapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.BluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.IBluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
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
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.AppSchedulerProvider;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 18/11/2017.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
//    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    ISchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }



    @Provides
    @PerActivity
    MainContract.View provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    @PerActivity
    MainContract.Interactor provideMainInteractor(MainInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    MainContract.Presenter<MainContract.View, MainContract.Interactor>
    provideMainPresenter(MainPresenter<MainContract.View, MainContract.Interactor> presenter) {
        return presenter;
    }



    @Provides
    BluetoothLeAdapter provideBluetoothLeAdapter() {
        if(Build.VERSION.SDK_INT >= 21) {
            return new BluetoothLe21Adapter();
        }
        else {
            return new BluetoothLe18Adapter();
        }
    }

    @Provides
    RxBleClient provideRxBleClient(Context context) {
        return RxBleClient.create(context);
    }

    @Provides
    Rx2BleClient provideRx2BleClient(RxBleClient client) {
        return new Rx2BleClient(client);
    }

    @Provides
    IBluetoothRepository provideBluetoothRepository(Rx2BleClient client) {
        return new BluetoothRepository(client);
    }

    @Provides
    @PerActivity
    BluetoothListContract.Interactor provideBluetoothListInteractor(BluetoothListInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    BluetoothListContract.Presenter<BluetoothListContract.View, BluetoothListContract.Interactor>
    provideBluetoothListPresenter(BluetoothListPresenter<BluetoothListContract.View, BluetoothListContract.Interactor> presenter) {
        return presenter;
    }




    @Provides
    @PerActivity
    ExerciseContract.Interactor provideExerciseInteractor(ExerciseInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ExerciseContract.Presenter<ExerciseContract.View, ExerciseContract.Interactor>
    provideExercisePresenter(ExercisePresenter<ExerciseContract.View, ExerciseContract.Interactor> presenter) {
        return presenter;
    }



    @Provides
    IExerciseTypeRepository provideExerciseTypeRepository(ExerciseTypeRepository repository) {
        return repository;
    }

    @Provides
    @PerActivity
    ExerciseTypesContract.View provideExerciseTypeView(ExerciseTypesFragment fragment) {
        return fragment;
    }

    @Provides
    @PerActivity
    ExerciseTypesContract.Interactor provideExerciseTypeInteractor(ExerciseTypesInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor>
    provideExerciseTypePresenter(ExerciseTypesPresenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor> presenter) {
        return presenter;
    }





    @Provides
    @PerActivity
    LoginContract.View provideLoginView(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    @PerActivity
    LoginContract.Interactor provideLoginInteractor(LoginInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>
    provideLoginPresenter(LoginPresenter<LoginContract.View, LoginContract.Interactor> presenter) {
        return presenter;
    }




    @Provides
    @PerActivity
    RegisterContract.View provideRegisterView(RegisterActivity activity) {
        return activity;
    }

    @Provides
    @PerActivity
    RegisterContract.Interactor provideRegisterInteractor(RegisterInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor>
    provideRegisterPresenter(RegisterPresenter<RegisterContract.View, RegisterContract.Interactor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    SplashContract.View provideSplashView(SplashActivity activity) {
        return activity;
    }

    @Provides
    @PerActivity
    SplashContract.Interactor provideSplashInteractor(SplashInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    SplashContract.Presenter<SplashContract.View, SplashContract.Interactor>
    providePresenter(SplashPresenter<SplashContract.View, SplashContract.Interactor> presenter) {
        return presenter;
    }




    @Provides
    @PerActivity
    TrainingContract.View provideTrainingView(TrainingActivity trainingActivity) {
        return trainingActivity;
    }

    @Provides
    @PerActivity
    TrainingContract.Interactor provideTrainingInteractor(TrainingInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    TrainingContract.Presenter<TrainingContract.View, TrainingContract.Interactor>
    provideTrainingPresenter(TrainingPresenter<TrainingContract.View, TrainingContract.Interactor> presenter) {
        return presenter;
    }
}
