package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseTypesInteractor extends BaseInteractor implements ExerciseTypesContract.Interactor {

    @Inject
    public ExerciseTypesInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<ExerciseType> doLoadExerciseTypes() {
        return getDataManager().getApiHelper().getExerciseService().doGetExerciseTypes();
    }

    @Override
    public Completable doSetExerciseType(final ExerciseType exerciseType) {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseCreator().
                flatMapCompletable(new Function<ExerciseCreator, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull final ExerciseCreator exerciseCreator) throws Exception {
                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                exerciseCreator.setExerciseType(exerciseType);
                            }
                        });
                    }
                });
    }
}
