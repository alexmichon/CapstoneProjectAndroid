package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import io.reactivex.Observable;

/**
 * Created by Alex on 30/12/2017.
 */

@Singleton
public class ExerciseTypeManager implements IExerciseTypeManager {

    private final IApiHelper mApiHelper;

    @Inject
    public ExerciseTypeManager(IApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<ExerciseType> loadExerciseTypes() {
        return mApiHelper.getExerciseService().doGetExerciseTypes();
    }
}
