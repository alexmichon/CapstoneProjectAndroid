package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import io.reactivex.Single;

/**
 * Created by Alex on 30/12/2017.
 */

@Singleton
public class ExerciseBuilderManager implements IExerciseBuilderManager {

    private IApiHelper mApiHelper;

    private Exercise.Builder mExerciseBuilder;

    @Inject
    public ExerciseBuilderManager(IApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }

    @Override
    public void setExerciseType(ExerciseType exerciseType) {
        mExerciseBuilder.withExerciseType(exerciseType);
    }

    @Override
    public Exercise.Builder getExerciseBuilder() {
        return mExerciseBuilder;
    }

    @Override
    public Single<Exercise> build() {
        return mApiHelper.getExerciseService().doCreateExercise(mExerciseBuilder);
    }
}
