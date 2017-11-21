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
        void onExerciseStart();
        void onExerciseStop();

        void addMeasurement(Measurement measurement);
    }

    interface Interactor extends IBaseInteractor {

        Completable doStartExercise(Exercise exercise);

        Flowable<Measurement> doListenEncoder();

        Flowable<Measurement> doListenImu();

        void doStopExercise();

        Flowable<Measurement> doListenMeasurements();

        Completable doSaveMeasurement(Exercise exercise, Measurement measurement);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        boolean isStarted();

        void onStartClick();

        void onStopClick();

        void setExerciseType(ExerciseType exerciseType);

        void onPause();
    }
}
