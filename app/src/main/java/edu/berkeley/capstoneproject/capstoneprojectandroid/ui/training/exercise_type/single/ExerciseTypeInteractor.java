package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.functions.Action;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeInteractor extends BaseInteractor implements ExerciseTypeContract.Interactor {

    @Inject
    public ExerciseTypeInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
