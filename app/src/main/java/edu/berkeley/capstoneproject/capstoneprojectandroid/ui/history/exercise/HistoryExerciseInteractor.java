package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IHistoryManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 18/01/2018.
 */

public class HistoryExerciseInteractor extends BaseInteractor implements HistoryExerciseContract.Interactor {

    private final IHistoryManager mHistoryManager;

    @Inject
    public HistoryExerciseInteractor(IHistoryManager historyManager) {
        mHistoryManager = historyManager;
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return mHistoryManager.doGetExerciseResult(exercise);
    }
}
