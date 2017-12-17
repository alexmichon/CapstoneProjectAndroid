package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;

/**
 * Created by Alex on 16/12/2017.
 */

public class HistoryExerciseFragmentFactory {

    public static HistoryExerciseFragment create(Exercise exercise) {
        return HistoryExerciseFragment.newInstance(exercise);
    }
}
