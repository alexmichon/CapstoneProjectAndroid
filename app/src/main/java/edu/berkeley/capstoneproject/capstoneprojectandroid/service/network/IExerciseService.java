package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IExerciseService {

    Single<Exercise> doCreateExercise(Exercise.Builder builder);
    Single<Measurement> doSaveMeasurement(Measurement measurement);

    //Single<ExerciseGoal> doCreateExerciseGoal(Exercise exercise, Exercise.Builder builder);
    //Single<ExerciseGoalCreator> doGetDefaultExerciseGoal(ExerciseType exerciseType);

    Single<ExerciseResult> doGetExerciseResult(Exercise exercise);

    Observable<ExerciseType> doGetExerciseTypes();
}
