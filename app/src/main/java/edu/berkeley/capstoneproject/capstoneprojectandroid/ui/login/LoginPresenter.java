package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.ApiService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RetroClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private Observable<User> mLoginSubscription;

    @Inject
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String email, String password) {
        mView.onLoginTry();

        final ApiService apiService = RetroClient.getApiService();
        mLoginSubscription = apiService.login(new LoginRequest(email, password));
        mLoginSubscription.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
