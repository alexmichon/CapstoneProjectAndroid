package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterResponse;
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
                .subscribe(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // TODO Convert RegisterResponse to User
                        User user = new User(email, password, firstName, lastName);
                        getView().hideLoading();
                        getView().onRegisterSuccess(user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().onError("Couldn't doRegisterApiCall");
                    }
                })
        );
    }

    @Override
    public void onRegisterCancel() {
        getCompositeDisposable().dispose();
    }
}
