package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseTypesInteractor extends BaseInteractor implements ExerciseTypesContract.Interactor {

    private final IExerciseTypeManager mExerciseTypeManager;

    @Inject
    public ExerciseTypesInteractor(IExerciseTypeManager exerciseTypeManager) {
        mExerciseTypeManager = exerciseTypeManager;
    }

    @Override
    public Observable<ExerciseType> doLoadExerciseTypes() {
        return mExerciseTypeManager.doGetExerciseTypes();
    }
}
