package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 07/11/2017.
 */

public interface RegisterContract extends AuthenticationFragmentContract {

    interface View extends AuthenticationFragmentContract.View {
    }

    interface Interactor extends AuthenticationFragmentContract.Interactor {
        Single<User> doRegisterApiCall(String email, String password, String passwordConfirmation, String firstName, String lastName);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends AuthenticationFragmentContract.Presenter<V, I> {
        void onRegisterClick(String email, String password, String passwordConfirmation, String firstName, String lastName);
    }
}
