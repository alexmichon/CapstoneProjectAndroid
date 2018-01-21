package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ITrainingManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 18/12/2017.
 */

public class ExerciseSummaryInteractor extends BaseInteractor implements ExerciseSummaryContract.Interactor {

    private final ITrainingManager mExerciseManager;

    @Inject
    public ExerciseSummaryInteractor(ITrainingManager exerciseManager) {
        mExerciseManager = exerciseManager;
    }

    @Override
    public ExerciseType getExerciseType() {
        return mExerciseManager.getExerciseType();
    }

    @Override
    public Exercise getExercise() {
        return mExerciseManager.getCurrentExercise();
    }

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal() {
        return mExerciseManager.doGetExerciseGoal();
    }
}
