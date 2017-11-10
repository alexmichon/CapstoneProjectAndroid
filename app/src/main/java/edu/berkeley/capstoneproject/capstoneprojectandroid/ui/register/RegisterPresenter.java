package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex on 07/11/2017.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private Observable<User> mRegisterSubscription;

    private AuthService mAuthService;

    @Inject
    public RegisterPresenter(RegisterContract.View view, AuthService authService) {
        super(view);
        mAuthService = authService;
    }

    @Override
    public void register(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        mRegisterSubscription = mAuthService.register(new RegisterRequest(
           email, password, passwordConfirmation, firstName, lastName
        ));

        mRegisterSubscription.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<User>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull User user) {
                    mView.onRegisterSuccess(user);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mView.onRegisterFailure();
                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    public void cancel() {
        if (mRegisterSubscription != null) {
            mRegisterSubscription.unsubscribeOn(Schedulers.io());
        }
    }
}
