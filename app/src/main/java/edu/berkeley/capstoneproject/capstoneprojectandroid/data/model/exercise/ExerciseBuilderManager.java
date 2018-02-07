package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

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
    public void setExerciseBuilder(Exercise.Builder builder) {
        mExerciseBuilder = builder;
    }

    @Override
    public Single<Exercise> doCreate() {
        return mApiHelper.getExerciseService().doCreateExercise(mExerciseBuilder)
                .doOnSuccess(new Consumer<Exercise>() {
                    @Override
                    public void accept(Exercise exercise) throws Exception {
                        exercise.setExerciseType(mExerciseBuilder.getExerciseType());
                    }
                });
    }
}
