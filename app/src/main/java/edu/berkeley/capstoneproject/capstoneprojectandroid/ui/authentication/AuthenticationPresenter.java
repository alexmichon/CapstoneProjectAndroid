package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 15/12/2017.
 */

public class AuthenticationPresenter<V extends AuthenticationContract.View, I extends AuthenticationContract.Interactor> extends BasePresenter<V, I> implements AuthenticationContract.Presenter<V, I> {

    @Inject
    public AuthenticationPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }
}
