package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
 * Created by Alex on 15/12/2017.
 */

public class RegisterFragment extends AuthenticationFragment<RegisterContract.View, RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor>> implements RegisterContract.View {

    @BindView(R.id.register_email)
    EditText mEmailEdit;

    @BindView(R.id.register_password)
    EditText mPasswordEdit;

    @BindView(R.id.register_password_confirmation)
    EditText mPasswordConfirmationEdit;

    @BindView(R.id.register_first_name)
    EditText mFirstNameEdit;

    @BindView(R.id.register_last_name)
    EditText mLastNameEdit;

    @BindView(R.id.register_remember)
    CheckBox mRememberCheckBox;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @OnClick(R.id.register_button)
    void onLoginClick() {
        getPresenter().onRegisterClick(
                mEmailEdit.getText().toString(),
                mPasswordEdit.getText().toString(),
                mPasswordConfirmationEdit.getText().toString(),
                mFirstNameEdit.getText().toString(),
                mLastNameEdit.getText().toString()
        );
    }

    @OnClick(R.id.register_login_link)
    void onRegisterLinkClick() {
        mAuthenticationListener.onSwitchToLogin();
    }

    @NonNull
    @Override
    public RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor> createPresenter() {
        return getActivityComponent().registerPresenter();
    }

    @Override
    public boolean remember() {
        return mRememberCheckBox.isChecked();
    }
}
