package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.Unbinder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;

/**
 * Created by Alex on 10/11/2017.
 */

public abstract class BaseFragment<V extends IBaseView, P extends IBasePresenter<V, ?>> extends MvpFragment<V, P> implements IBaseView {

    private BaseActivity mActivity;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void showLoading(@StringRes int stringRes) {
        if (mActivity != null) {
            mActivity.showLoading(stringRes);
        }
    }

    @Override
    public void showLoading(String message) {
        if (mActivity != null) {
            mActivity.showLoading(message);
        }
    }

    @Override
    public void showLoading(String message, boolean cancelable) {
        if (mActivity != null) {
            mActivity.showLoading(message, cancelable);
        }
    }

    @Override
    public void showLoading(@StringRes int stringRes, boolean cancelable) {
        if (mActivity != null) {
            mActivity.showLoading(stringRes, cancelable);
        }
    }

    @Override
    public void showLoading(String message, OnCancelListener listener) {
        if (mActivity != null) {
            mActivity.showLoading(message, listener);
        }
    }

    @Override
    public void showLoading(@StringRes int stringRes, OnCancelListener listener) {
        if (mActivity != null) {
            mActivity.showLoading(stringRes, listener);
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void showError(String message) {
        if (mActivity != null) {
            mActivity.showError(message);
        }
    }

    @Override
    public void showError(@StringRes int stringRes) {
        if (mActivity != null) {
            mActivity.showError(stringRes);
        }
    }

    @Override
    public void showError(Throwable throwable) {
        if (mActivity != null) {
            mActivity.showError(throwable);
        }
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int stringRes) {
        if (mActivity != null) {
            mActivity.showMessage(stringRes);
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public void setUnbinder(Unbinder unbinder) {
        mUnbinder = unbinder;
    }

    @Override
    public void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    public abstract String getTitle();
}
