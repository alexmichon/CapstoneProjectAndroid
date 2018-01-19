package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by Alex on 10/11/2017.
 */

public interface ExerciseContract {

    interface View extends IBaseView {
        void onExerciseCreated(Exercise exercise);
        void onExerciseStarted(Exercise exercise);
        void onExerciseStopped(Exercise exercise);

        void addMeasurement(Measurement measurement);

        void onCreatingExercise();
        void onStartingExercise();

        void onExerciseError(Throwable throwable);
    }

    interface Interactor extends IBaseInteractor {

        //Single<ExerciseGoal> doCreateExerciseGoal();

        //Single<Exercise> doCreateExercise();

        Exercise getExercise();

        Completable doStartExercise();

        Completable doStartStreaming();

        Completable doStopExercise();

        Flowable<Measurement> doListenMeasurements();

        void doSaveMeasurement(Measurement measurement);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        boolean isStarted();

        void onStartClick();

        void onStopClick();

        void onPause();
    }
}
