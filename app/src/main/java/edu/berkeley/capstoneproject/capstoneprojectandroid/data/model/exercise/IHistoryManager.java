package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 18/01/2018.
 */

public interface IHistoryManager {

    Observable<Exercise> doGetExercises();
    Single<ExerciseResult> doGetExerciseResult(Exercise exercise);
}
