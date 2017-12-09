package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Observable;

/**
 * Created by Alex on 08/11/2017.
 */

public interface ExerciseTypesContract {

    interface View extends IBaseView {
        void addExerciseType(ExerciseType exerciseType);
        void startExerciseTypeActivity(ExerciseType exerciseType);

        void onExerciseTypeMore(ExerciseType exerciseType);
        void onExerciseTypeStart(ExerciseType exerciseType);

        void onExerciseTypesLoading();
        void onExerciseTypesDoneLoading();
    }

    interface Interactor extends IBaseInteractor {
        Observable<ExerciseType> doLoadExerciseTypes();
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onLoadExerciseTypes();
        void onExerciseTypeMoreClick(ExerciseType exerciseType);

        void onExerciseTypeStartClick(ExerciseType exerciseType);
    }
}