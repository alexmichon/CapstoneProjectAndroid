package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomeFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuItem;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainActivity extends ToolbarActivity<MainContract.View, MainContract.Presenter<MainContract.View, MainContract.Interactor>> implements MainContract.View, MainMenuFragment.MainMenuItemListener, HomeFragment.HomeFragmentListener {

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private MainMenuFragment mMainMenuFragment;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnbinder(ButterKnife.bind(this));

        mMainMenuFragment = (MainMenuFragment) getSupportFragmentManager().findFragmentById(R.id.main_menu_fragment);
        mMainMenuFragment.setListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            showHomeFragment();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    protected void setFragment(BaseFragment fragment) {
        setFragment(R.id.main_container, fragment);
    }

    @Override
    public void showHomeFragment() {
        setFragment(HomeFragment.newInstance(this));
    }

    @NonNull
    @Override
    public MainContract.Presenter<MainContract.View, MainContract.Interactor> createPresenter() {
        return getActivityComponent().mainPresenter();
    }

    @Override
    public void onMainMenuItemClick(MainMenuItem item) {
        getPresenter().onMainMenuItemClick(item);
    }

    @Override
    public void onStartTrainingActivity() {
        Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
        startActivity(intent);
    }
}
