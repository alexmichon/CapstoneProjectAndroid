package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ITrainingManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 18/01/2018.
 */

public class TrainingExerciseResultInteractor extends BaseInteractor implements TrainingExerciseResultContract.Interactor {

    private final ITrainingManager mTrainingManager;

    @Inject
    public TrainingExerciseResultInteractor(ITrainingManager exerciseManager) {
        mTrainingManager = exerciseManager;
    }

    @Override
    public Exercise getExercise() {
        return mTrainingManager.getCurrentExercise();
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult() {
        return mTrainingManager.doGetExerciseResult();
    }
}
