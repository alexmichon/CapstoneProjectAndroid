package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryExercisesInteractor extends BaseInteractor implements HistoryExercisesContract.Interactor {

    @Inject
    public HistoryExercisesInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<Exercise> doGetExercises() {
        return getDataManager().getApiHelper().getExerciseService().doGetExercises();
    }
}
