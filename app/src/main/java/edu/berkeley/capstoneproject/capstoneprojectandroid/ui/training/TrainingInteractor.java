package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingInteractor extends BaseInteractor implements TrainingContract.Interactor {

    @Inject
    public TrainingInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
