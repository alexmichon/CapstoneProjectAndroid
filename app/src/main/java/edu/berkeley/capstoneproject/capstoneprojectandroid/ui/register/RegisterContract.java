package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface RegisterContract {

    interface View extends IBaseView {
        void onRegisterTry();
        void onRegisterSuccess(User user);
        void onRegisterFailure();
    }

    interface Interactor extends IBaseInteractor {
        Observable<RegisterResponse> doRegisterApiCall(RegisterRequest request);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onRegisterClick(String email, String password, String passwordConfirmation, String firstName, String lastName);
        void onRegisterCancel();
    }
}
