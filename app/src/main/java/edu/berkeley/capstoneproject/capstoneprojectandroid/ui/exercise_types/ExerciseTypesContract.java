package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;

/**
 * Created by Alex on 08/11/2017.
 */

public interface ExerciseTypesContract {

    interface View {
        void addExerciseType(ExerciseType exerciseType);
        void showError(String message);
        void startExerciseTypeActivity(ExerciseType exerciseType);
    }

    interface Presenter {
        void loadExerciseTypes();
        void onExerciseTypeClick(ExerciseType exerciseType);
    }
}
