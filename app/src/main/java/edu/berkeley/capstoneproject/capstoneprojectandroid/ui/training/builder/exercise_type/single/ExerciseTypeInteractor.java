package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.single;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeInteractor extends BaseInteractor implements ExerciseTypeContract.Interactor {

    @Inject
    public ExerciseTypeInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
