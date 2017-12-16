package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 11/11/2017.
 */

public class HomePresenter<V extends HomeContract.View, I extends HomeContract.Interactor>
        extends BasePresenter<V, I> implements HomeContract.Presenter<V, I> {


    @Inject
    public HomePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onStartTrainingClick() {
        getView().startTrainingActivity();
    }

    @Override
    public void onViewResultsClick() {
        getView().showError("Not implemented yet !");
    }
}
