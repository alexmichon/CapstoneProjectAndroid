package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 15/12/2017.
 */

public abstract class AuthenticationFragmentPresenter<V extends AuthenticationFragmentContract.View, I extends AuthenticationFragmentContract.Interactor>
        extends BasePresenter<V, I> implements AuthenticationFragmentContract.Presenter<V, I> {

    public AuthenticationFragmentPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAuthenticationSuccess(User user) {
        if (isViewAttached()) {
            if (getView().remember()) {
                getInteractor().doRemember(user);
            }

            getView().onAuthenticationSuccess(user);
        }
    }

    @Override
    public void onAuthenticationFailure(Throwable throwable) {
        if (isViewAttached()) {
            onAuthenticationFailure(throwable);
        }
    }

    @Override
    public void onAuthenticationCancel() {
        getCompositeDisposable().dispose();
    }
}
