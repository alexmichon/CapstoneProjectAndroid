package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register.RegisterContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.HistoryContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise.HistoryExerciseContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises.HistoryExercisesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.ExerciseBuilderContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise.ExerciseContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result.ExerciseResultContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.home.HomeContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result.TrainingExerciseResultContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary.ExerciseSummaryContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list.ExerciseTypesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type.ExerciseTypeContract;

/**
 * Created by Alex on 18/11/2017.
 */

@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    MainContract.Presenter<MainContract.View, MainContract.Interactor> mainPresenter();
    BluetoothListContract.Presenter<BluetoothListContract.View,BluetoothListContract.Interactor> bluetoothListPresenter();
    ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor> exerciseTypesPresenter();
    LoginContract.Presenter<LoginContract.View,LoginContract.Interactor> loginPresenter();
    RegisterContract.Presenter<RegisterContract.View,RegisterContract.Interactor> registerPresenter();
    SplashContract.Presenter<SplashContract.View,SplashContract.Interactor> splashPresenter();
    TrainingContract.Presenter<TrainingContract.View,TrainingContract.Interactor> trainingPresenter();
    ExerciseContract.Presenter<ExerciseContract.View,ExerciseContract.Interactor> exercisePresenter();
    HistoryContract.Presenter<HistoryContract.View,HistoryContract.Interactor> historyPresenter();
    HistoryExercisesContract.Presenter<HistoryExercisesContract.View,HistoryExercisesContract.Interactor> historyExercisesPresenter();
    HistoryExerciseContract.Presenter<HistoryExerciseContract.View,HistoryExerciseContract.Interactor> historyExercisePresenter();
    ExerciseTypeContract.Presenter<ExerciseTypeContract.View,ExerciseTypeContract.Interactor> exerciseTypePresenter();
    AuthenticationContract.Presenter<AuthenticationContract.View,AuthenticationContract.Interactor> authenticationPresenter();
    MainMenuContract.Presenter<MainMenuContract.View,MainMenuContract.Interactor> mainMenuPresenter();
    HomeContract.Presenter<HomeContract.View,HomeContract.Interactor> homePresenter();
    ExerciseGoalContract.Presenter<ExerciseGoalContract.View,ExerciseGoalContract.Interactor> exerciseGoalPresenter();
    ExerciseResultContract.Presenter<ExerciseResultContract.View,ExerciseResultContract.Interactor> exerciseResultPresenter();
    ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View,ExerciseSummaryContract.Interactor> exerciseSummaryPresenter();
    ExerciseBuilderContract.Presenter<ExerciseBuilderContract.View, ExerciseBuilderContract.Interactor> exerciseBuilderPresenter();
    TrainingExerciseResultContract.Presenter<TrainingExerciseResultContract.View,TrainingExerciseResultContract.Interactor> trainingExerciseResultPresenter();
}
