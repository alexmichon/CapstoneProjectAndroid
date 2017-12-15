package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 15/12/2017.
 */

public abstract class AuthenticationFragment<V extends AuthenticationFragmentContract.View,P extends AuthenticationFragmentContract.Presenter<V,?>> extends BaseFragment<V, P> implements AuthenticationFragmentContract.View {

    protected Listener mAuthenticationListener;

    public void setListener(Listener listener) {
        mAuthenticationListener = listener;
    }

    public interface Listener {
        void onAuthenticationSuccess(User user);
        void onSwitchToLogin();
        void onSwitchToRegister();
    }



    @Override
    public void onAuthenticationStart(OnCancelListener listener) {
        showLoading("Authenticating...", listener);
    }

    @Override
    public void onAuthenticationSuccess(User user) {
        hideLoading();
        showMessage("Welcome " + user.getFirstName());
        mAuthenticationListener.onAuthenticationSuccess(user);
    }

    @Override
    public void onAuthenticationFailure(Throwable throwable) {
        hideLoading();
        showError("An error occurred !");
    }
}
