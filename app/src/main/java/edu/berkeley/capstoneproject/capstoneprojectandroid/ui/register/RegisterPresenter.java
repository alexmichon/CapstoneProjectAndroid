package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 07/11/2017.
 */

public class RegisterPresenter<V extends RegisterContract.View, I extends RegisterContract.Interactor>
        extends BasePresenter<V, I> implements RegisterContract.Presenter<V, I> {


    @Inject
    public RegisterPresenter(I interactor,
                             ISchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onRegisterClick(final String email, final String password, String passwordConfirmation, final String firstName, final String lastName) {
        getView().showLoading();
        getCompositeDisposable().add(getInteractor()
                .doRegisterApiCall(new RegisterRequest(email, password, passwordConfirmation, firstName, lastName))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        getView().hideLoading();
                        getView().onRegisterSuccess(user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().showError("Couldn't doRegisterApiCall");
                    }
                })
        );
    }

    @Override
    public void onRegisterCancel() {
        getCompositeDisposable().dispose();
    }
}
