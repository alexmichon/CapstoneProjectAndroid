package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.support.v7.app.AppCompatActivity;

import org.mockito.Mockito;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;

/**
 * Created by Alex on 25/11/2017.
 */

public class TestActivityModule extends ActivityModule {

    private final boolean mMockMode;

    public TestActivityModule(AppCompatActivity activity, boolean mockMode) {
        super(activity);
        mMockMode = mockMode;
    }

    @Override
    LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> provideLoginPresenter(LoginPresenter<LoginContract.View, LoginContract.Interactor> presenter) {
        if (mMockMode) {
            return Mockito.mock(LoginContract.Presenter.class);
        }
        return super.provideLoginPresenter(presenter);
    }
}
