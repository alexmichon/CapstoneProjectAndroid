package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 18/12/2017.
 */

public class ExerciseSummaryInteractor extends BaseInteractor implements ExerciseSummaryContract.Interactor {

    @Inject
    public ExerciseSummaryInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<ExerciseGoal> doGetCurrentExerciseGoal() {
        return getDataManager().getSessionHelper().getExerciseService().getCurrentExerciseGoal();
    }

    @Override
    public Completable doSetExerciseGoal(ExerciseGoal exerciseGoal) {
        return getDataManager().getSessionHelper().getExerciseService().setCurrentExerciseGoal(exerciseGoal);
    }
}
