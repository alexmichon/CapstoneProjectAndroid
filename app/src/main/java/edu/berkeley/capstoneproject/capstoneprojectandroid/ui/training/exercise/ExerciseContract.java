package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
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
        void onExerciseReady(Exercise exercise);
        void onExerciseError(Throwable throwable);
        void onExerciseFinished();
        void onExerciseStopped();

        void onCreatingExercise();
        void onPreparingExercise();

        void onStartRecording();

        void addMeasurement(Measurement measurement);

        void onCountdownStart();
        void onCountdownUpdate(int count);
        void onCountdownFinished();

        void setTimerMax(float timerMax);
        void onTimerStart();
        void onTimerUpdate(float time);
        void onTimerFinished();
    }

    interface Interactor extends IBaseInteractor {

        //Single<ExerciseGoal> doCreateExerciseGoal();

        //Single<Exercise> doCreateExercise();

        Exercise getExercise();

        Completable doPrepareExercise();

        Completable doStartStreaming();

        Completable doStopExercise();

        Flowable<Measurement> doListen();

        void doSaveMeasurement(Measurement measurement);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        boolean isRecording();

        void onViewReady();

        void onStopRecording();

        void onPause();

        void onStartClick();
        void onStopClick();

        void onStartRecording();
    }
}
