package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 08/11/2017.
 */

public interface ExerciseTypesContract {

    interface View extends IBaseView {
        void addExerciseType(ExerciseType exerciseType);

        void selectExerciseType(ExerciseType exerciseType);

        void onExerciseTypesLoading();
        void onExerciseTypesDoneLoading();

        void showExerciseTypeDialog(ExerciseType exerciseType);
        void dismissExerciseTypeDialog();

        void onExerciseTypesError(Throwable e);
    }

    interface Interactor extends IBaseInteractor {
        Observable<ExerciseType> doLoadExerciseTypes();
        Completable doSetExerciseType(ExerciseType exerciseType);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onLoadExerciseTypes();
        void onExerciseTypeMore(ExerciseType exerciseType);

        void onExerciseTypeSelect(ExerciseType exerciseType);

        void onExerciseTypeDialogBack();
        void onExerciseTypeDialogSelect(ExerciseType exerciseType);
    }
}
