package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ITrainingManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.IMeasurementManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseInteractor extends BaseInteractor implements ExerciseContract.Interactor {

    private final ITrainingManager mExerciseManager;

    @Inject
    public ExerciseInteractor(ITrainingManager exerciseManager) {
        mExerciseManager = exerciseManager;
    }

    @Override
    public Exercise getExercise() {
        return mExerciseManager.getCurrentExercise();
    }

    @Override
    public Completable doPrepareExercise() {
        return mExerciseManager.doStartSensors();
    }

    @Override
    public Completable doStartStreaming() {
        return mExerciseManager.doStartStreaming();
    }

    @Override
    public Completable doStopExercise() {
        return Completable.concatArray(
                mExerciseManager.doStopStreaming().andThen(mExerciseManager.doStopExercise()),
                mExerciseManager.doStopSensors());
    }


    @Override
    public Flowable<Measurement> doListen() {
        return mExerciseManager.doListen();
    }

    @Override
    public void doSaveMeasurement(final Measurement measurement) {
        mExerciseManager.doSendMeasurement(measurement);
    }
}
