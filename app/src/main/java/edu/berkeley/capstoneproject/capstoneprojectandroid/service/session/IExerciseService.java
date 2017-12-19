package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 17/12/2017.
 */

public interface IExerciseService {

    Single<Exercise> getCurrentExercise();
    Completable setCurrentExercise(Exercise exercise);

    Single<ExerciseType> getCurrentExerciseType();
    Completable setCurrentExerciseType(ExerciseType exerciseType);

    Single<ExerciseGoal> getCurrentExerciseGoal();
    Completable setCurrentExerciseGoal(ExerciseGoal exerciseGoal);

    Completable clear();
}
