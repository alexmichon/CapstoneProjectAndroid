package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import io.reactivex.Observable;

/**
 * Created by Alex on 30/12/2017.
 */

public interface IExerciseTypeManager {

    Observable<ExerciseType> doGetExerciseTypes();
}
