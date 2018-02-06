package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginPresenter<V extends LoginContract.View, I extends LoginContract.Interactor>
        extends AuthenticationFragmentPresenter<V, I> implements LoginContract.Presenter<V, I> {

    @Inject
    public LoginPresenter(I interactor,
                          ISchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLoginClick(final String email, final String password) {
        if (isViewAttached()) {
            getView().onAuthenticationStart(new IBaseView.OnCancelListener() {
                @Override
                public void onCancel() {
                    onAuthenticationCancel();
                }
            });

            getInteractor().remember(getView().remember());
        }

        getCompositeDisposable().add(
                getInteractor().doLogin(email, password)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                onAuthenticationSuccess(user);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                onAuthenticationFailure(throwable);
                            }
                        })
        );
    }
}
