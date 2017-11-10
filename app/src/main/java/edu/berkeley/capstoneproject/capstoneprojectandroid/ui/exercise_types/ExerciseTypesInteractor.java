package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseTypesInteractor extends BaseInteractor implements ExerciseTypesContract.Interactor {

    private final IExerciseTypeRepository mExerciseTypeRepository;

    @Inject
    public ExerciseTypesInteractor(IExerciseTypeRepository exerciseTypeRepository) {
        mExerciseTypeRepository = exerciseTypeRepository;
    }

    @Override
    public Observable<ExerciseType> doLoadExerciseTypes() {
        return mExerciseTypeRepository.query();
    }
}
