package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 15/12/2017.
 */

public interface AuthenticationFragmentContract {

    interface View extends IBaseView {
        boolean remember();

        void onAuthenticationStart(OnCancelListener listener);
        void onAuthenticationSuccess(User user);
        void onAuthenticationFailure(Throwable throwable);
    }

    interface Interactor extends IBaseInteractor {
        void doRemember(User user);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onAuthenticationSuccess(User user);
        void onAuthenticationFailure(Throwable throwable);
        void onAuthenticationCancel();
    }
}
