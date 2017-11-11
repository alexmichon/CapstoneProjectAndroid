package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainActivity extends DrawerActivity implements MainContract.View, HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;


    @Inject
    MainContract.Presenter<MainContract.View, MainContract.Interactor> mPresenter;

    @BindView(R.id.main_text_hello)
    TextView mHelloView;

    @BindView(R.id.main_button_start_exercise)
    Button mStartExerciseButton;

    @BindView(R.id.main_button_view_results)
    Button mViewResultsButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        mPresenter.onAttach(this);

        // TODO
        setNavigationDrawerMenu(R.menu.main_navigation, mPresenter.getNavigationListener());
        initNavigationDrawer();
    }


    @OnClick(R.id.main_button_start_exercise)
    void onStartExerciseClick() {
        mPresenter.onStartExerciseClick();
    }


    @Override
    public void showError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startBluetoothListActivity() {
        Intent intent = new Intent(MainActivity.this, BluetoothListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }
}
