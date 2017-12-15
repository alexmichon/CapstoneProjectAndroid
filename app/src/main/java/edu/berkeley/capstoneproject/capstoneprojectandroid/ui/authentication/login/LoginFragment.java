package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragment;

/**
 * Created by Alex on 04/12/2017.
 */

public class LoginFragment extends AuthenticationFragment<LoginContract.View, LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>> implements LoginContract.View {

    @BindView(R.id.login_email)
    EditText mEmailEdit;

    @BindView(R.id.login_password)
    EditText mPasswordEdit;

    @BindView(R.id.login_remember)
    CheckBox mRememberCheckBox;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @OnClick(R.id.login_button)
    void onLoginClick() {
        getPresenter().onLoginClick(
                mEmailEdit.getText().toString(),
                mPasswordEdit.getText().toString()
        );
    }

    @OnClick(R.id.login_register_link)
    void onRegisterLinkClick() {
        mAuthenticationListener.onSwitchToRegister();
    }

    @Override
    public String getTitle() {
        return "Login";
    }

    @Override
    public LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> createPresenter() {
        return getActivityComponent().loginPresenter();
    }

    @Override
    public boolean remember() {
        return mRememberCheckBox.isChecked();
    }
}
