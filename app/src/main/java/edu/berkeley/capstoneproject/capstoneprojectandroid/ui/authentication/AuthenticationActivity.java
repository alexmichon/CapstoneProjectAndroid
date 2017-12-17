package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login.LoginFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register.RegisterFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;

/**
 * Created by Alex on 15/12/2017.
 */

public class AuthenticationActivity extends ToolbarActivity<AuthenticationContract.View, AuthenticationContract.Presenter<AuthenticationContract.View, AuthenticationContract.Interactor>>
    implements AuthenticationContract.View, AuthenticationFragment.Listener {

    public static final String EXTRA_UID = "EXTRA_UID";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        String uid = getIntent().getStringExtra(EXTRA_UID);

        if (savedInstanceState == null) {
            setFragment(LoginFragment.newInstance());
        }
    }

    protected void setFragment(AuthenticationFragment fragment) {
        setFragment(R.id.authentication_container, fragment);
        fragment.setListener(this);
    }

    @NonNull
    @Override
    public AuthenticationContract.Presenter<AuthenticationContract.View, AuthenticationContract.Interactor> createPresenter() {
        return getActivityComponent().authenticationPresenter();
    }

    @Override
    public void onAuthenticationSuccess(User user) {
        Intent intent = new Intent(AuthenticationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSwitchToLogin() {
        setFragment(new LoginFragment());
        setTitle("Login");
    }

    @Override
    public void onSwitchToRegister() {
        setFragment(new RegisterFragment());
        setTitle("Register");
    }
}
