package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.app.ProgressDialog;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Alex on 07/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private ProgressDialog mProgressDialog;

    @Override
    public void showMessage(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int stringRes) {
        showMessage(getString(stringRes));
    }

    @Override
    public void onError(String message) {
        showMessage(message);
    }

    @Override
    public void onError(@StringRes int stringRes) {
        showMessage(stringRes);
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
