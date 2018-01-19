package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultPresenter<V extends ExerciseResultContract.View, I extends ExerciseResultContract.Interactor> extends BasePresenter<V, I> implements ExerciseResultContract.Presenter<V, I> {

    private ExerciseResult mExerciseResult;

    @Inject
    public ExerciseResultPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadExerciseResultInfo() {
        if (isViewAttached()) {
            getView().onExerciseResultLoaded(mExerciseResult);
        }
    }

    @Override
    public void setExerciseResult(ExerciseResult exerciseResult) {
        mExerciseResult = exerciseResult;
    }
}
