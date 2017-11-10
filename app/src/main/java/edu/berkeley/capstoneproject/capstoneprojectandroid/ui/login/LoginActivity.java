package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register.RegisterActivity;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginActivity extends ToolbarActivity implements LoginContract.View {

    @BindView(R.id.login_email)
    EditText mEmailEdit;

    @BindView(R.id.login_password)
    EditText mPasswordEdit;

    @BindView(R.id.login_register_link)
    TextView mRegisterLink;

    @BindView(R.id.login_button)
    Button mLoginButton;

    private ProgressDialog mProgressDialog;

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    void onLoginClick() {
        mPresenter.login(
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoginTry() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Authenticating...");
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mPresenter.cancel();
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.show();
    }

    @Override
    public void onLoginSuccess(User user) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(LoginActivity.this, "Welcome back " + user.getFirstName() + " !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(LoginActivity.this, "An error occurred !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
