package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;

/**
 * Created by Alex on 07/11/2017.
 */

public class SplashActivity extends BaseActivity<SplashContract.View, SplashContract.Presenter<SplashContract.View, SplashContract.Interactor>> implements SplashContract.View {

    private static final int PERMISSION_REQUEST = 1;

    private static final String[] PERMISSIONS = new String[] {
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };


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
        Intent intent = new Intent(SplashActivity.this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void moveToAuthenticationActivity(Authentication authentication) {
        Intent intent = new Intent(SplashActivity.this, AuthenticationActivity.class);
        if (authentication != null) {
            intent.putExtra(AuthenticationActivity.EXTRA_UID, authentication.getUid());
        }

        startActivity(intent);
        finish();
    }

    @Override
    public void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        for (String permission: PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        permission)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.
                    permissions.add(permission);


                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        }

        if (!permissions.isEmpty()) {
            String[] perms = new String[permissions.size()];
            ActivityCompat.requestPermissions(this,
                    permissions.toArray(perms),
                    PERMISSION_REQUEST);
        }
        else {
            getPresenter().onPermissionCheckDone();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case PERMISSION_REQUEST:
                for (int i = 0; i < permissions.length; i++) {
                    getPresenter().onPermissionResult(permissions[i], grantResults[i]);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getPresenter().onPermissionCheckDone();
                    }
                });
                break;
        }
    }

    @Override
    public void moveToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
