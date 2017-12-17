package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IExerciseService {

    Single<Exercise> doCreateExercise(ExerciseType exerciseType);
    Single<Measurement> doSaveMeasurement(Measurement measurement);

    Single<Measurement> getMaxMeasurement();

    Single<ExerciseGoal> doUpdateExerciseGoal(ExerciseGoal exerciseGoal);

    Single<ExerciseGoal> doGetExerciseGoal(ExerciseType exerciseType);

    Single<ExerciseResult> doGetExerciseResult(Exercise exercise);

    Observable<ExerciseType> doGetExerciseTypes();
}
