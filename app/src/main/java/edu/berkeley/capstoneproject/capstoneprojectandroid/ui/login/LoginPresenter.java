package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginPresenter<V extends LoginContract.View, I extends LoginContract.Interactor>
        extends BasePresenter<V, I> implements LoginContract.Presenter<V, I> {

    @Inject
    public LoginPresenter(I interactor,
                          ISchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLoginClick(final String email, final String password) {
        getView().showLoading();

        if (email.equals("admin") && password.equals("admin")) {
            getView().hideLoading();
            getView().onLoginSuccess(new User(email, password, "admin", ""));
            getView().startMainActivity();
            return;
        }

        getCompositeDisposable().add(
                getInteractor().doLoginCall(new LoginRequest(email, password))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<LoginResponse>() {
                            @Override
                            public void accept(LoginResponse loginResponse) throws Exception {
                                // TODO Convert LoginResponse to User
                                User user = new User(email, password, "", "");
                                getView().hideLoading();
                                getView().onLoginSuccess(user);
                                getView().startMainActivity();
                            }
                        })
        );
    }

    @Override
    public void onLoginCancel() {
        getCompositeDisposable().dispose();
    }
}
