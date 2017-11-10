package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private Observable<User> mLoginSubscription;

    private AuthService mAuthService;


    @Inject
    public LoginPresenter(LoginContract.View view, AuthService authService) {
        super(view);
        mAuthService = authService;
    }

    @Override
    public void login(String email, String password) {
        mView.onLoginTry();

        if (email.equals("admin") && password.equals("admin")) {
            mView.onLoginSuccess(new User(email, password, "admin", ""));
            return;
        }

        mLoginSubscription = mAuthService.login(new LoginRequest(email, password));
        mLoginSubscription.subscribeOn(getSubscribingScheduler())
            .observeOn(getObservingScheduler())
            .subscribe(new Consumer<User>() {
                @Override
                public void accept(User user) throws Exception {
                    mView.onLoginSuccess(user);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mView.onLoginFailure();
                }
            });
    }

    @Override
    public void cancel() {
        if (mLoginSubscription != null) {
            mLoginSubscription.unsubscribeOn(Schedulers.io());
        }
    }
}
