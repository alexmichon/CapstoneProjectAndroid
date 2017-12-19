package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalInteractor extends BaseInteractor implements ExerciseGoalContract.Interactor {

    @Inject
    public ExerciseGoalInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<ExerciseGoal> doUpdateExerciseGoal(final List<MetricGoal> metricGoals) {
        return getDataManager().getSessionHelper().getExerciseService().getCurrentExerciseGoal()
                .flatMap(new Function<ExerciseGoal, SingleSource<? extends ExerciseGoal>>() {
                    @Override
                    public SingleSource<? extends ExerciseGoal> apply(@NonNull ExerciseGoal exerciseGoal) throws Exception {
                        exerciseGoal.setMetricGoals(metricGoals);
                        return getDataManager().getApiHelper().getExerciseService().doUpdateExerciseGoal(exerciseGoal);
                    }
                });
    }

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal() {
        return getDataManager().getSessionHelper().getExerciseService().getCurrentExerciseType()
                .flatMap(new Function<ExerciseType, SingleSource<? extends ExerciseGoal>>() {
                    @Override
                    public SingleSource<? extends ExerciseGoal> apply(@NonNull ExerciseType exerciseType) throws Exception {
                        return getDataManager().getApiHelper().getExerciseService().doGetExerciseGoal(exerciseType);
                    }
                });
    }

    @Override
    public Completable doSetExerciseGoal(ExerciseGoal exerciseGoal) {
        return getDataManager().getSessionHelper().getExerciseService().setCurrentExerciseGoal(exerciseGoal);
    }
}
