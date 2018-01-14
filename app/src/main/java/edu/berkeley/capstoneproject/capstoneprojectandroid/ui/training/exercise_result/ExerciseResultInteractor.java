package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultInteractor extends BaseInteractor implements ExerciseResultContract.Interactor {

    @Inject
    public ExerciseResultInteractor() {
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult() {
        // TODO
        /*return getDataManager().getSessionHelper().getTrainingService().getExercise()
                .flatMap(new Function<Exercise, SingleSource<? extends ExerciseResult>>() {
                    @Override
                    public SingleSource<? extends ExerciseResult> apply(@NonNull Exercise exercise) throws Exception {
                        return getDataManager().getApiHelper().getExerciseService().doGetExerciseResult(exercise);
                    }
                });*/
        return Single.never();
    }
}
