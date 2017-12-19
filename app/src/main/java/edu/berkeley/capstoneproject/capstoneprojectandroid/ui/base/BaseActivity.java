package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

import butterknife.Unbinder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;

/**
 * Created by Alex on 07/11/2017.
 */

public abstract class BaseActivity<V extends IBaseView, P extends IBasePresenter<V, ?>> extends MvpActivity<V, P> implements IBaseView {

    private ProgressDialog mProgressDialog;

    private Unbinder mUnbinder;

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    public ActivityComponent getActivityComponent() {
        return ((CapstoneProjectAndroidApplication)getApplication()).getActivityComponent(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int stringRes) {
        showMessage(getString(stringRes));
    }

    @Override
    public void showError(String message) {
        showMessage(message);
    }

    @Override
    public void showError(@StringRes int stringRes) {
        showMessage(stringRes);
    }

    @Override
    public void showError(Throwable throwable) {
        showMessage(throwable.getMessage());
    }

    @Override
    public void showLoading() {
        showLoading("Loading...");
    }

    @Override
    public void showLoading(@StringRes int stringRes) {
        showLoading(getString(stringRes));
    }

    @Override
    public void showLoading(String message) {
        hideLoading();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    @Override
    public void showLoading(String message, boolean cancelable) {
        showLoading(message);
        mProgressDialog.setCancelable(cancelable);
    }

    @Override
    public void showLoading(@StringRes int stringRes, boolean cancelable) {
        showLoading(getString(stringRes), cancelable);
    }

    @Override
    public void showLoading(String message, final OnCancelListener listener) {
        showLoading(message);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                listener.onCancel();
            }
        });
    }

    @Override
    public void showLoading(@StringRes int stringRes, OnCancelListener listener) {
        showLoading(getString(stringRes), listener);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void setFragment(@IdRes int containerViewId, BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(containerViewId, fragment).commit();
    }

    public void showDialog(BaseDialog dialog, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialog.show(fragmentManager, tag);
    }

    public void setUnbinder(Unbinder unBinder) {
        mUnbinder = unBinder;
    }
}
