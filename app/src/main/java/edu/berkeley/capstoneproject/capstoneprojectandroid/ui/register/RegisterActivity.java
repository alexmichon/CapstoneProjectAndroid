package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;

/**
 * Created by Alex on 07/11/2017.
 */

// TODO
public class RegisterActivity extends ToolbarActivity implements RegisterContract.View{

    @Inject
    RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor> mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_register);
        setUnbinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
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

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
