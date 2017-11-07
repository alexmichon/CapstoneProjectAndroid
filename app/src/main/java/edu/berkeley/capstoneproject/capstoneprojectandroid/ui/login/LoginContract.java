package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

/**
 * Created by Alex on 06/11/2017.
 */

public interface LoginContract {

    interface View {
        public void onLoginTry();
        public void onLoginSuccess();
        public void onLoginFailure();
    }

    interface Presenter {
        public void login(String email, String password);
        public void cancel();
    }
}
