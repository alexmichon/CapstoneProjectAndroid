package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;

/**
 * Created by Alex on 06/11/2017.
 */

public interface LoginContract {

    interface View {
        public void onLoginTry();
        public void onLoginSuccess(User user);
        public void onLoginFailure();

        public void startMainActivity();
    }

    interface Presenter {
        public void login(String email, String password);
        public void cancel();
    }
}
