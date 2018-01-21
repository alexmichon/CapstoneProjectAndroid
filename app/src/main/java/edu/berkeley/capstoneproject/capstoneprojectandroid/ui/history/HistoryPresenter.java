package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryPresenter<V extends HistoryContract.View, I extends HistoryContract.Interactor> extends BasePresenter<V, I> implements HistoryContract.Presenter<V, I> {

    @Inject
    public HistoryPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onHistoryExerciseSelect(Exercise exercise) {
        if (isViewAttached()) {
            getView().showHistoryExerciseFragment(exercise);
        }
    }
}
