package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 06/11/2017.
 */

public interface LoginContract {

    interface View extends IBaseView {
        void onLoginSuccess(User user);
        void onLoginFailure();

        void startMainActivity();
    }

    interface Interactor extends IBaseInteractor {
        Single<User> doLoginCall(LoginRequest loginRequest);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onLoginClick(String email, String password);
        void onLoginCancel();
    }
}
