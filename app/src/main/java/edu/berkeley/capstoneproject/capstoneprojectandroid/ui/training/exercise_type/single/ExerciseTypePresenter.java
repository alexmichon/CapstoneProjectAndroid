package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypePresenter<V extends IBaseView, I extends IBaseInteractor> extends BasePresenter<V, I> implements ExerciseTypeContract.Presenter<V, I> {

    @Inject
    public ExerciseTypePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }
}
