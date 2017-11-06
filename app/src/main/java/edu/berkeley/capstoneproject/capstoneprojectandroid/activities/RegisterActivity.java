package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.TimeUnit;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers.UserHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.requests.ApiRequest;

/**
 * Created by Alex on 06/11/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private EditText mEmailEdit;
    private EditText mPasswordEdit;
    private EditText mPasswordConfirmationEdit;
    private EditText mFirstEdit;
    private EditText mLastEdit;

    private Button mRegisterButton;

    private ProgressDialog mProgressDialog;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RegisterThread.HANDLER_MESSAGE_REGISTER:
                    switch(msg.arg1) {
                        case RegisterThread.HANDLER_REGISTER_SUCCESS:
                            onRegisterSuccess();
                            break;
                        case RegisterThread.HANDLER_REGISTER_FAILURE:
                            onRegisterFailure();
                            break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailEdit = findViewById(R.id.register_email);
        mPasswordEdit = findViewById(R.id.register_password);
        mPasswordConfirmationEdit = findViewById(R.id.register_password_confirmation);
        mFirstEdit = findViewById(R.id.register_first_name);
        mLastEdit = findViewById(R.id.register_last_name);

        mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register() {
        Log.d(TAG, "Register");

        User user = new User(
            mEmailEdit.getText().toString(),
            mPasswordEdit.getText().toString(),
            mPasswordConfirmationEdit.getText().toString(),
            mFirstEdit.getText().toString(),
            mLastEdit.getText().toString()
        );

        final RegisterThread registerThread = new RegisterThread(user);

        mProgressDialog = new ProgressDialog(RegisterActivity.this, R.style.Theme_AppCompat_Dialog);
        mProgressDialog.setMessage("Registration in progress...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                registerThread.cancel();
            }
        });

        mProgressDialog.show();

        registerThread.start();
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    public void onRegisterSuccess() {
        mProgressDialog.dismiss();
        Toast.makeText(RegisterActivity.this, "Welcome !", Toast.LENGTH_SHORT).show();
    }


    public void onRegisterFailure() {
        mProgressDialog.dismiss();
        Toast.makeText(RegisterActivity.this, "Registration failed !", Toast.LENGTH_SHORT).show();
    }


    private class RegisterThread extends Thread {

        public static final int HANDLER_MESSAGE_REGISTER = 0;
        public static final int HANDLER_REGISTER_SUCCESS = 0;
        public static final int HANDLER_REGISTER_FAILURE = 1;

        private final User mUser;

        private RequestFuture mFuture;

        public RegisterThread(User user) {
            mUser = user;
        }

        @Override
        public void run() {
            try {
                mFuture = RequestFuture.newFuture();

                ApiRequest request = UserHelper.register(mUser, mFuture);
                mFuture.get(30, TimeUnit.SECONDS);

                Log.d(TAG, "Authenticated");

                mUser.authenticate(request);
                CapstoneProjectAndroidApplication.getInstance().setCurrentUser(mUser);

                Message msg = mHandler.obtainMessage();
                msg.what = HANDLER_MESSAGE_REGISTER;
                msg.arg1 = HANDLER_REGISTER_SUCCESS;
                msg.sendToTarget();

            } catch (Exception e) {
                Log.e(TAG, "Registration Error", e);
                mUser.setAuthenticated(false);
                Message msg = mHandler.obtainMessage();
                msg.what = HANDLER_MESSAGE_REGISTER;
                msg.arg1 = HANDLER_REGISTER_FAILURE;
                msg.sendToTarget();
            }
        }

        public void cancel() {
            mFuture.cancel(true);
        }
    }
}
