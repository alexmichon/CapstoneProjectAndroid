package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalInteractor extends BaseInteractor implements ExerciseGoalContract.Interactor {

    private final IExerciseManager mExerciseManager;

    @Inject
    public ExerciseGoalInteractor(IExerciseManager exerciseManager) {
        mExerciseManager = exerciseManager;
    }

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal() {
        return mExerciseManager.doGetExerciseGoal();
    }

}
