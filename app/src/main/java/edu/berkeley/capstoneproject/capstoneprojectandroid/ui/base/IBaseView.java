package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Alex on 09/11/2017.
 */

public interface IBaseView {

    void showLoading();
    void showLoading(String message);
    void showLoading(@StringRes int stringRes);
    void showLoading(String message, OnCancelListener listener);
    void showLoading(@StringRes int stringRes, OnCancelListener listener);
    void showLoading(String message, boolean cancelable);
    void showLoading(@StringRes int stringRes, boolean cancelable);

    void hideLoading();

    void showMessage(String message);
    void showMessage(@StringRes int stringRes);

    void showError(String message);
    void showError(@StringRes int stringRes);
    void showError(Throwable throwable);

    interface OnCancelListener {
        void onCancel();
    }
}
