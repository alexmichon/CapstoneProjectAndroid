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

    public static final String EXTRA_UID = "EXTRA_UID";



    @BindView(R.id.login_email)
    EditText mEmailEdit;

    @BindView(R.id.login_password)
    EditText mPasswordEdit;

    @BindView(R.id.login_remember)
    CheckBox mRememberCheckBox;



    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(new Bundle());
        return loginFragment;
    }

    public static LoginFragment newInstance(String uid) {
        LoginFragment loginFragment = new LoginFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_UID, uid);
        loginFragment.setArguments(args);

        return loginFragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        Bundle args = getArguments();
        String uid = args.getString(EXTRA_UID, null);
        if (uid != null) {
            mEmailEdit.setText(uid);
        }

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
    public LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> createPresenter() {
        return getActivityComponent().loginPresenter();
    }

    @Override
    public boolean remember() {
        return mRememberCheckBox.isChecked();
    }
}
