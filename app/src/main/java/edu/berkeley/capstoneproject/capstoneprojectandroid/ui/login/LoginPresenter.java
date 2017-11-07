package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void login(String email, String password) {

    }

    @Override
    public void cancel() {

    }
}
