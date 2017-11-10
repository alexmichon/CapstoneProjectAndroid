package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;

/**
 * Created by Alex on 07/11/2017.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.splash_text_info)
    TextView mTextInfo;

    @Inject
    SplashContract.Presenter<SplashContract.View, SplashContract.Interactor> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void updateMessage(String msg) {
        mTextInfo.setText(msg);
    }

    @Override
    public void promptEnableNetwork() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
        startActivity(intent);
    }

    @Override
    public void stop() {
        finish();
    }

    @Override
    public void done() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
