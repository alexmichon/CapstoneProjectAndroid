package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Observable;

/**
 * Created by Alex on 06/12/2017.
 */

public interface HistoryExercisesContract {

    interface View extends IBaseView {
        void addExercise(Exercise exercise);
        void onLoadingExercises(OnCancelListener cancelListener);
        void onExercisesLoaded();
    }

    interface Interactor extends IBaseInteractor {
        Observable<Exercise> doGetExercises();
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        void loadExercises();
    }
}
