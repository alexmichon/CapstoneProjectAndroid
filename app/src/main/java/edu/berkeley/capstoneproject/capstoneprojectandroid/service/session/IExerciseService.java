package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 17/12/2017.
 */

public interface IExerciseService {

    Exercise getCurrentExercise();
    void setCurrentExercise(Exercise exercise);

    ExerciseType getCurrentExerciseType();
    void setCurrentExerciseType(ExerciseType exerciseType);

    ExerciseGoal getCurrentExerciseGoal();
    void setCurrentExerciseGoal(ExerciseGoal exerciseGoal);

    void clear();
}
