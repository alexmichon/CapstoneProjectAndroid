package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 16/12/2017.
 */

public class HistoryExercisePresenter<V extends HistoryExerciseContract.View, I extends HistoryExerciseContract.Interactor> extends BasePresenter<V, I> implements HistoryExerciseContract.Presenter<V, I> {

    @Inject
    public HistoryExercisePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }
}
