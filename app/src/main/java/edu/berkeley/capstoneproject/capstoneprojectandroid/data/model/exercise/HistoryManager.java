package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 18/01/2018.
 */

@Singleton
public class HistoryManager implements IHistoryManager {

    private final IApiHelper mApiHelper;

    @Inject
    public HistoryManager(IApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<Exercise> doGetExercises() {
        return mApiHelper.getExerciseService().doGetExercises();
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return mApiHelper.getExerciseService().doGetExerciseResult(exercise);
    }
}
