package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 13/01/2018.
 */

public class ExerciseBuilderInteractor extends BaseInteractor implements ExerciseBuilderContract.Interactor {

    @Inject
    public ExerciseBuilderInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
