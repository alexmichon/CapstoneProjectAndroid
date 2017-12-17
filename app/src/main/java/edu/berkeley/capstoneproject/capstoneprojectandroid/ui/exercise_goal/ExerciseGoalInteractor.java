package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_goal;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalInteractor extends BaseInteractor implements ExerciseGoalContract.Interactor {

    @Inject
    public ExerciseGoalInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Completable doSaveExerciseGoal(ExerciseGoal exerciseGoal) {
        return getDataManager().getApiHelper().getExerciseService().doSaveExerciseGoal(exerciseGoal).toCompletable();
    }
}
