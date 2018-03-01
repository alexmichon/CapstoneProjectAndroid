package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Alex on 30/12/2017.
 */

public interface ITrainingManager {

    Exercise getCurrentExercise();

    void setCurrentExercise(Exercise exercise);

    ExerciseType getExerciseType();

    void setExerciseType(ExerciseType exerciseType);

    Completable doStartSensors(Rx2BleConnection connection);
    Completable doStopSensors();
    Completable doStopExercise();

    Flowable<Measurement> doListen();

    boolean isListening();

    Completable doStartStreaming();
    Completable doStopStreaming();
    void doSendMeasurement(Measurement measurement);

    Single<ExerciseGoal> doGetExerciseGoal();
    Single<ExerciseResult> doGetExerciseResult();
}
