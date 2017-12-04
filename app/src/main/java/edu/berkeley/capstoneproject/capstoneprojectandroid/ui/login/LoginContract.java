package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 06/11/2017.
 */

public interface LoginContract {

    interface View extends IBaseView {
        void onLoginSuccess(User user);

        void onLoginFailure();
        void onLoginFailure(Throwable throwable);
        void onLoginFailure(String message);

        void startMainActivity();

        void onLoginStart(OnCancelListener listener);
    }

    interface Interactor extends IBaseInteractor {
        Single<User> doLoginCall(String email, String password);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onLoginClick(String email, String password);
        void onLoginCancel();
    }
}
