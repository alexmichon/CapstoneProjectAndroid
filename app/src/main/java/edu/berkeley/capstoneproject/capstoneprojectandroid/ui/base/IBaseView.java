package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Alex on 09/11/2017.
 */

public interface IBaseView {

    void showLoading();
    void hideLoading();

    void showMessage(String message);
    void showMessage(@StringRes int stringRes);

    void onError(String message);
    void onError(@StringRes int stringRes);
}
