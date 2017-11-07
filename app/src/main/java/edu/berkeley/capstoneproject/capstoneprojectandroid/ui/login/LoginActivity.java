package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText mEmailEdit;
    private EditText mPasswordEdit;

    private Button mLoginButton;

    private ProgressDialog mProgressDialog;

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

        mPresenter = new LoginPresenter(this);
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
    public void onLoginSuccess() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(LoginActivity.this, "Welcome back !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(LoginActivity.this, "An error occurred !", Toast.LENGTH_SHORT).show();
    }
}
