package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.support.v7.app.AppCompatActivity;

import org.mockito.Mockito;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;

/**
 * Created by Alex on 25/11/2017.
 */

public class TestActivityModule extends ActivityModule {

    public TestActivityModule(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> provideLoginPresenter(LoginPresenter<LoginContract.View, LoginContract.Interactor> presenter) {
        return Mockito.mock(LoginContract.Presenter.class);
    }
}
