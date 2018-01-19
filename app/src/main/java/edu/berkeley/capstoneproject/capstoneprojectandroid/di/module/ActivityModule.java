package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login.LoginInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login.LoginPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register.RegisterContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register.RegisterInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register.RegisterPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.HistoryContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.HistoryInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.HistoryPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise.HistoryExerciseContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise.HistoryExerciseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise.HistoryExercisePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises.HistoryExercisesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises.HistoryExercisesInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises.HistoryExercisesPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.ExerciseBuilderContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.ExerciseBuilderInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.ExerciseBuilderPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise.ExerciseContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise.ExerciseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise.ExercisePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result.ExerciseResultContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result.ExerciseResultInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result.ExerciseResultPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result.TrainingExerciseResultContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result.TrainingExerciseResultInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result.TrainingExerciseResultPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary.ExerciseSummaryContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary.ExerciseSummaryInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary.ExerciseSummaryPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list.ExerciseTypesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list.ExerciseTypesFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list.ExerciseTypesInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list.ExerciseTypesPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.home.HomeContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.home.HomeInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.home.HomePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type.ExerciseTypeContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type.ExerciseTypeInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type.ExerciseTypePresenter;
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
    @PerActivity
    MainMenuContract.Interactor provideMainMenuInteractor(MainMenuInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    MainMenuContract.Presenter<MainMenuContract.View, MainMenuContract.Interactor>
    provideMainMenuPresenter(MainMenuPresenter<MainMenuContract.View, MainMenuContract.Interactor> presenter) {
        return presenter;
    }




    @Provides
    @PerActivity
    HomeContract.Interactor provideHomeInteractor(HomeInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>
    provideHomePresenter(HomePresenter<HomeContract.View, HomeContract.Interactor> presenter) {
        return presenter;
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
    ExerciseTypesContract.View provideExerciseTypesView(ExerciseTypesFragment fragment) {
        return fragment;
    }

    @Provides
    @PerActivity
    ExerciseTypesContract.Interactor provideExerciseTypesInteractor(ExerciseTypesInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor>
    provideExerciseTypesPresenter(ExerciseTypesPresenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor> presenter) {
        return presenter;
    }




    @Provides
    @PerActivity
    AuthenticationContract.Interactor provideAuthenticationInteractor(AuthenticationInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    AuthenticationContract.Presenter<AuthenticationContract.View, AuthenticationContract.Interactor>
    provideAuthenticationPresenter(AuthenticationPresenter<AuthenticationContract.View, AuthenticationContract.Interactor> presenter) {
        return presenter;
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





    @Provides
    @PerActivity
    ExerciseGoalContract.Interactor provideExerciseGoalInteractor(ExerciseGoalInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor>
    provideExerciseGoalPresenter(ExerciseGoalPresenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor> presenter) {
        return presenter;
    }





    @Provides
    @PerActivity
    ExerciseBuilderContract.Interactor provideExerciseBuilderInteractor(ExerciseBuilderInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerActivity
    ExerciseBuilderContract.Presenter<ExerciseBuilderContract.View, ExerciseBuilderContract.Interactor>
    provideExerciseBuilderPresenter(ExerciseBuilderPresenter<ExerciseBuilderContract.View, ExerciseBuilderContract.Interactor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    ExerciseTypeContract.Interactor provideExerciseTypeInteractor(ExerciseTypeInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor>
    provideExerciseTypePresenter(ExerciseTypePresenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    ExerciseSummaryContract.Interactor provideExerciseSummaryInteractor(ExerciseSummaryInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor>
    provideExerciseSummaryPresenter(ExerciseSummaryPresenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    ExerciseResultContract.Interactor provideExerciseResultInteractor(ExerciseResultInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    ExerciseResultContract.Presenter<ExerciseResultContract.View, ExerciseResultContract.Interactor>
    provideExerciseResultPresenter(ExerciseResultPresenter<ExerciseResultContract.View, ExerciseResultContract.Interactor> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    TrainingExerciseResultContract.Interactor provideTrainingExerciseResultInteractor(TrainingExerciseResultInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    TrainingExerciseResultContract.Presenter<TrainingExerciseResultContract.View, TrainingExerciseResultContract.Interactor>
    provideTrainingExerciseResultPresenter(TrainingExerciseResultPresenter<TrainingExerciseResultContract.View, TrainingExerciseResultContract.Interactor> presenter) {
        return presenter;
    }







    @Provides
    @PerActivity
    HistoryContract.Interactor provideHistoryInteractor(HistoryInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    HistoryContract.Presenter<HistoryContract.View, HistoryContract.Interactor> provideHistoryPresenter(HistoryPresenter<HistoryContract.View, HistoryContract.Interactor> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    HistoryExercisesContract.Presenter<HistoryExercisesContract.View, HistoryExercisesContract.Interactor>
    provideHistoryExercisesPresenter(HistoryExercisesPresenter<HistoryExercisesContract.View, HistoryExercisesContract.Interactor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    HistoryExercisesContract.Interactor provideHistoryExercisesInteractor(HistoryExercisesInteractor interactor) {
        return interactor;
    }



    @Provides
    @PerActivity
    HistoryExerciseContract.Interactor provideHistoryExerciseInteractor(HistoryExerciseInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    HistoryExerciseContract.Presenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor>
    provideHistoryExercisePresenter(HistoryExercisePresenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor> presenter) {
        return presenter;
    }
}