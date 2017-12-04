package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
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
        getView().onLoginStart(new IBaseView.OnCancelListener() {
            @Override
            public void onCancel() {
                onLoginCancel();
            }
        });

        // TODO: Move to fake
        if (email.equals("admin") && password.equals("admin")) {
            getView().onLoginSuccess(new User(email, "admin", ""));
            getView().startMainActivity();
            return;
        }

        getCompositeDisposable().add(
                getInteractor().doLoginCall(email, password)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                getView().onLoginSuccess(user);
                                getView().startMainActivity();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().onLoginFailure(throwable);
                                handleApiError(throwable);
                            }
                        })
        );
    }

    @Override
    public void onLoginCancel() {
        getCompositeDisposable().dispose();
    }
}
