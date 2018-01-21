package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseBuilderManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ITrainingManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 13/01/2018.
 */

public class ExerciseBuilderInteractor extends BaseInteractor implements ExerciseBuilderContract.Interactor {

    private final IExerciseBuilderManager mExerciseBuilderManager;
    private final ITrainingManager mExerciseManager;

    @Inject
    public ExerciseBuilderInteractor(IExerciseBuilderManager exerciseBuilderManager, ITrainingManager exerciseManager) {
        mExerciseBuilderManager = exerciseBuilderManager;
        mExerciseManager = exerciseManager;
    }

    @Override
    public Single<Exercise> doCreateExercise(final ExerciseType exerciseType) {
        mExerciseBuilderManager.setExerciseBuilder(new Exercise.Builder()
                .withExerciseType(exerciseType)
        );
        return mExerciseBuilderManager.doCreate()
                .doOnSuccess(new Consumer<Exercise>() {
                    @Override
                    public void accept(Exercise exercise) throws Exception {
                        mExerciseManager.setCurrentExercise(exercise);
                        mExerciseManager.setExerciseType(exerciseType);
                    }
                });
    }
}
