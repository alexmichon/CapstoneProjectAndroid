package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.tasks.LoginThread;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User.HANDLER_LOGIN_FAILURE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User.HANDLER_LOGIN_SUCCESS;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User.HANDLER_MESSAGE_LOGIN;

/**
 * Created by Alex on 05/11/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button mLoginButton;
    private EditText mEmailEdit;
    private EditText mPasswordEdit;

    private SharedPreferences mLoginPreferences;

    private ProgressDialog mProgressDialog;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_MESSAGE_LOGIN:
                    switch(msg.what) {
                        case HANDLER_LOGIN_FAILURE:
                            onLoginFailure();
                            break;
                        case HANDLER_LOGIN_SUCCESS:
                            onLoginSuccess();
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

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
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
        final LoginThread loginThread = new LoginThread(mHandler, user);

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
}
