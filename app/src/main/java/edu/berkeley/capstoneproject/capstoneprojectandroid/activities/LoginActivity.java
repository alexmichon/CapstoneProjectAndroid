package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.TimeUnit;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers.UserHelper;

/**
 * Created by Alex on 05/11/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button mLoginButton;
    private EditText mEmailEdit;
    private EditText mPasswordEdit;

    private TextView mRegisterLink;

    private ProgressDialog mProgressDialog;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LoginThread.HANDLER_MESSAGE_LOGIN:
                    switch(msg.arg1) {
                        case LoginThread.HANDLER_LOGIN_SUCCESS:
                            onLoginSuccess();
                            break;
                        case LoginThread.HANDLER_LOGIN_FAILURE:
                            onLoginFailure();
                            break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "Created activity");

        mLoginButton = (Button) findViewById(R.id.login_button);
        mEmailEdit = (EditText) findViewById(R.id.login_email);
        mPasswordEdit = (EditText) findViewById(R.id.login_password);
        mRegisterLink = (TextView) findViewById(R.id.login_register_link);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    public void onLoginSuccess() {
        mProgressDialog.dismiss();
        Toast.makeText(LoginActivity.this, "Welcome back !", Toast.LENGTH_SHORT).show();
    }

    public void onLoginFailure() {
        mProgressDialog.dismiss();
        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
    }

    public void login() {
        Log.d(TAG, "Login");

        User user = new User(mEmailEdit.getText().toString(), mPasswordEdit.getText().toString());
        final LoginThread loginThread = new LoginThread(user);

        mProgressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_Dialog);
        mProgressDialog.setMessage("Authenticating...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                loginThread.cancel();
            }
        });

        mProgressDialog.show();

        loginThread.start();
    }


    private class LoginThread extends Thread {

        public static final int HANDLER_MESSAGE_LOGIN = 0;
        public static final int HANDLER_LOGIN_SUCCESS = 0;
        public static final int HANDLER_LOGIN_FAILURE = 1;

        private final User mUser;

        private RequestFuture mFuture;

        public LoginThread(User user) {
            mUser = user;
        }

        @Override
        public void run() {
            try {
                mFuture = UserHelper.login(mUser.getEmail(), mUser.getPassword());
                mFuture.get(30, TimeUnit.SECONDS);

                Log.d(TAG, "Authenticated");

                mUser.setAuthenticated(true);
                CapstoneProjectAndroidApplication.getInstance().setCurrentUser(mUser);

                mHandler.obtainMessage(HANDLER_MESSAGE_LOGIN, HANDLER_LOGIN_SUCCESS).sendToTarget();

            } catch (Exception e) {
                Log.e(TAG, "Login Error", e);
                mUser.setAuthenticated(false);
                mHandler.obtainMessage(HANDLER_MESSAGE_LOGIN, HANDLER_LOGIN_FAILURE).sendToTarget();
            }
        }

        public void cancel() {
            mFuture.cancel(true);
        }
    }
}
