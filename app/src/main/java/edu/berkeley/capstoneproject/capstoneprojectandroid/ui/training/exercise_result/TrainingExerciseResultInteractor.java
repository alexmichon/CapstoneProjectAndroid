package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 18/01/2018.
 */

public class TrainingExerciseResultInteractor extends BaseInteractor implements TrainingExerciseResultContract.Interactor {

    private final IExerciseManager mExerciseManager;

    @Inject
    public TrainingExerciseResultInteractor(IExerciseManager exerciseManager) {
        mExerciseManager = exerciseManager;
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult() {
        return mExerciseManager.doGetExerciseResult();
    }
}
