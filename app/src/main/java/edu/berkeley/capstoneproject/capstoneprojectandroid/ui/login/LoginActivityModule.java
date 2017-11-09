package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class LoginActivityModule {

    @Provides
    LoginContract.View provideView(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    LoginContract.Presenter providePresenter(LoginContract.View view) {
        return new LoginPresenter(view);
    }
}
