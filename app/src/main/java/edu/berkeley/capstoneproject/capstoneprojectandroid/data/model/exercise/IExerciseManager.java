package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Alex on 30/12/2017.
 */

public interface IExerciseManager {

    Exercise getCurrentExercise();

    void setCurrentExercise(Exercise exercise);

    ExerciseType getExerciseType();

    void setExerciseType(ExerciseType exerciseType);

    Completable doStartSensors();
    Completable doStopSensors();
    Completable doStopExercise();

    Flowable<Measurement> listen();

    Completable doStartStreaming();
    Completable doStopStreaming();
    void doSendMeasurement(Measurement measurement);

    Single<ExerciseGoal> doGetExerciseGoal();
}
