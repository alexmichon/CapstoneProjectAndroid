package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register.RegisterActivity;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginActivity extends ToolbarActivity<LoginContract.View, LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>> implements LoginContract.View {

    @BindView(R.id.login_email)
    EditText mEmailEdit;

    @BindView(R.id.login_password)
    EditText mPasswordEdit;

    @BindView(R.id.login_register_link)
    TextView mRegisterLink;

    @BindView(R.id.login_button)
    Button mLoginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUnbinder(ButterKnife.bind(this));
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
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @NonNull
    @Override
    public LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> createPresenter() {
        return getActivityComponent().loginPresenter();
    }

    @Override
    public void onLoginStart(OnCancelListener listener) {
        showLoading("Authenticating...", listener);
    }

    @Override
    public void onLoginSuccess(User user) {
        hideLoading();
        showMessage("Welcome back " + user.getFirstName());
    }

    @Override
    public void onLoginFailure() {
        hideLoading();
        showError("An error occurred !");
    }

    @Override
    public void onLoginFailure(Throwable throwable) {
        hideLoading();
        showError(throwable.getMessage());
    }

    @Override
    public void onLoginFailure(String message) {
        hideLoading();
        showError(message);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
