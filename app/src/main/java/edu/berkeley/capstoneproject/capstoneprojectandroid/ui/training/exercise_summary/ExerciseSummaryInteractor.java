package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 18/12/2017.
 */

public class ExerciseSummaryInteractor extends BaseInteractor implements ExerciseSummaryContract.Interactor {

    @Inject
    public ExerciseSummaryInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
