package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 17/12/2017.
 */

@Singleton
public class ExerciseService implements IExerciseService {

    private Exercise mExercise;
    private ExerciseType mExerciseType;
    private ExerciseGoal mExerciseGoal;

    @Inject
    public ExerciseService() {

    }

    @Override
    public Exercise getCurrentExercise() {
        return mExercise;
    }

    @Override
    public void setCurrentExercise(Exercise exercise) {
        mExercise = exercise;
    }

    @Override
    public ExerciseType getCurrentExerciseType() {
        return mExerciseType;
    }

    @Override
    public void setCurrentExerciseType(ExerciseType exerciseType) {
        mExerciseType = exerciseType;
    }

    @Override
    public ExerciseGoal getCurrentExerciseGoal() {
        return mExerciseGoal;
    }

    @Override
    public void setCurrentExerciseGoal(ExerciseGoal exerciseGoal) {
        mExerciseGoal = exerciseGoal;
    }

    @Override
    public void clear() {
        mExercise = null;
        mExerciseGoal = null;
        mExerciseType = null;
    }
}
