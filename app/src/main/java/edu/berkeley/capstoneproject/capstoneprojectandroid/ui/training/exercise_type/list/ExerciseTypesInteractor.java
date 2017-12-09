package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseTypesInteractor extends BaseInteractor implements ExerciseTypesContract.Interactor {

    private final IExerciseTypeRepository mExerciseTypeRepository;

    @Inject
    public ExerciseTypesInteractor(IDataManager dataManager, IExerciseTypeRepository exerciseTypeRepository) {
        super(dataManager);
        mExerciseTypeRepository = exerciseTypeRepository;
    }

    @Override
    public Observable<ExerciseType> doLoadExerciseTypes() {
        return getDataManager().getApiHelper().getExerciseService().getExerciseTypes();
    }
}
