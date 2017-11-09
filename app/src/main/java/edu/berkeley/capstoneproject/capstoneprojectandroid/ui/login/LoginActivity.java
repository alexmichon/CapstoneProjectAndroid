package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListActivity;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private EditText mEmailEdit;
    private EditText mPasswordEdit;

    private Button mLoginButton;

    private ProgressDialog mProgressDialog;

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailEdit = findViewById(R.id.login_email);
        mPasswordEdit = findViewById(R.id.login_password);
        mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login(
                        mEmailEdit.getText().toString(),
                        mPasswordEdit.getText().toString()
                );
            }
        });
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
        Intent intent = new Intent(LoginActivity.this, BluetoothListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(LoginActivity.this, "An error occurred !", Toast.LENGTH_SHORT).show();
    }
}
