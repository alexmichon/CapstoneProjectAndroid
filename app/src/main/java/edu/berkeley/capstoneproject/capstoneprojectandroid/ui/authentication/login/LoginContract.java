package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentContract;
import io.reactivex.Single;

/**
 * Created by Alex on 06/11/2017.
 */

public interface LoginContract extends AuthenticationFragmentContract {

    interface View extends AuthenticationFragmentContract.View {
    }

    interface Interactor extends AuthenticationFragmentContract.Interactor {
        Single<User> doLogin(String email, String password);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends AuthenticationFragmentContract.Presenter<V, I> {
        void onLoginClick(String email, String password);
    }
}
