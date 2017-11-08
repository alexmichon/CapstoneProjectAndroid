package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;

/**
 * Created by Alex on 07/11/2017.
 */

public interface RegisterContract {

    interface View {
        void onRegisterTry();
        void onRegisterSuccess(User user);
        void onRegisterFailure();
    }

    interface Presenter {
        void register(String email, String password, String passwordConfirmation, String firstName, String lastName);
        void cancel();
    }
}
