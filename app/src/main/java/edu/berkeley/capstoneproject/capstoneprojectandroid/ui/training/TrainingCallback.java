package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;

/**
 * Created by Alex on 18/11/2017.
 */

public interface TrainingCallback {

    void gotoBluetoothList();
    void gotoExerciseTypes();
    void gotoExercise(ExerciseType exerciseType);
}
