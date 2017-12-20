package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 10/11/2017.
 */

public interface ExerciseContract {

    interface View extends IBaseView {
        ExerciseType getExerciseType();

        void onExerciseCreated(Exercise exercise);
        void onExerciseReady(Exercise exercise);
        void onExerciseStopped(Exercise exercise);
        void onExerciseError(Throwable throwable);
        void onExerciseFinished();

        void onCreatingExercise();
        void onStartingExercise();

        void onStartRecording();

        void addMeasurement(Measurement measurement);

        void showCountdown(int countdown);
        void showDuration(int duration);

        void onCountdownFinished();
    }

    interface Interactor extends IBaseInteractor {

        Single<Exercise> doCreateExercise(ExerciseType exerciseType);

        Completable doStartExercise(Exercise exercise);

        Flowable<Measurement> doListenEncoder();

        Flowable<Measurement> doListenImu();

        void doStopExercise();

        Flowable<Measurement> doListenMeasurements();

        Completable doSaveMeasurement(Exercise exercise, Measurement measurement);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        boolean isRecording();

        void onCreate();

        void onStopRecording();

        void onPause();

        void onStartClick();
        void onStopClick();

        void onStartRecording();
    }
}
