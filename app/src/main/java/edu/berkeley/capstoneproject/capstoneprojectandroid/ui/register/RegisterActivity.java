package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;

/**
 * Created by Alex on 07/11/2017.
 */

// TODO
public class RegisterActivity extends ToolbarActivity<RegisterContract.View, RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor>> implements RegisterContract.View{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUnbinder(ButterKnife.bind(this));
    }

    @Override
    public void onRegisterTry() {

    }

    @Override
    public void onRegisterSuccess(User user) {

    }

    @Override
    public void onRegisterFailure() {

    }

    @NonNull
    @Override
    public RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor> createPresenter() {
        return getActivityComponent().registerPresenter();
    }
}
