package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;

/**
 * Created by Alex on 07/11/2017.
 */

public class SplashActivity extends BaseActivity<SplashContract.View, SplashContract.Presenter<SplashContract.View, SplashContract.Interactor>> implements SplashContract.View {

    @BindView(R.id.splash_text_info)
    TextView mTextInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUnbinder(ButterKnife.bind(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onStart();
    }

    @NonNull
    @Override
    public SplashContract.Presenter<SplashContract.View, SplashContract.Interactor> createPresenter() {
        return getActivityComponent().splashPresenter();
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
}
