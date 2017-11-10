package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;

/**
 * Created by Alex on 08/11/2017.
 */

public interface ExercisesContract {

    interface View {
        void addExercise(Exercise exercise);
        void showError(String message);
    }

    interface Presenter {
        void loadExercises();
        void onExerciseClick(Exercise exercise);
    }
}
