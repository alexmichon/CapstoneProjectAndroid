package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by Alex on 30/12/2017.
 */

public interface IExerciseManager {

    Exercise getCurrentExercise();

    void setCurrentExercise(Exercise exercise);

    Completable start();
    Completable stop();

    Flowable<Measurement> listen();
}
