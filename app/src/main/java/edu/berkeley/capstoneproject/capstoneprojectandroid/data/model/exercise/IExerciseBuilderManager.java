package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import io.reactivex.Single;

/**
 * Created by Alex on 30/12/2017.
 */

public interface IExerciseBuilderManager {

    Exercise.Builder getExerciseBuilder();

    void setExerciseType(ExerciseType exerciseType);

    Single<Exercise> build();
}
