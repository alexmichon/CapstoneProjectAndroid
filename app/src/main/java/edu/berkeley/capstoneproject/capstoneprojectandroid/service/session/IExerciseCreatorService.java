package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 17/12/2017.
 */

public interface IExerciseCreatorService {

    Single<ExerciseCreator> getExerciseCreator();
    Completable newExerciseCreator(ExerciseType exerciseType);

    Single<ExerciseGoalCreator> getExerciseGoalCreator();
    Completable newExerciseGoalCreator(ExerciseType exerciseType);

    Completable clear();
}
