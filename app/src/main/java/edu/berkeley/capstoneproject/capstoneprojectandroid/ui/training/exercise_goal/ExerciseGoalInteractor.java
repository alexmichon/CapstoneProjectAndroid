package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
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

    @Inject
    public ExerciseGoalInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<ExerciseGoalCreator> doLoadDefaultExerciseGoal() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseCreator().flatMap(new Function<ExerciseCreator, SingleSource<? extends ExerciseType>>() {
            @Override
                public SingleSource<? extends ExerciseType> apply(@NonNull ExerciseCreator exerciseCreator) throws Exception {
                        return Single.just(exerciseCreator.getExerciseType());
                }
        }).flatMap(new Function<ExerciseType, SingleSource<? extends ExerciseGoalCreator>>() {
                @Override
                public SingleSource<? extends ExerciseGoalCreator> apply(@NonNull ExerciseType exerciseType) throws Exception {
                        return getDataManager().getApiHelper().getExerciseService().doGetDefaultExerciseGoal(exerciseType);
                }
        });
    }

    @Override
    public Single<ExerciseGoalCreator> doLoadCurrentExerciseGoal() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseGoalCreator();
    }

    @Override
    public Completable doUpdateExerciseGoal(final ExerciseGoal.Type type, final List<MetricGoal> metricGoals) {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseGoalCreator()
                .flatMapCompletable(new Function<ExerciseGoalCreator, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull final ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                exerciseGoalCreator.setType(type);
                                exerciseGoalCreator.setMetricGoals(metricGoals);
                            }
                        });
                    }
                });
    }
}
