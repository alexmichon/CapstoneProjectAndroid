package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 18/01/2018.
 */

public class HistoryExerciseInteractor extends BaseInteractor implements HistoryExerciseContract.Interactor {

    @Inject
    public HistoryExerciseInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
