package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomeFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuItem;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;
import timber.log.Timber;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainActivity extends DrawerActivity<MainContract.View, MainContract.Presenter<MainContract.View, MainContract.Interactor>> implements MainContract.View, MainMenuFragment.MainMenuItemListener, HomeFragment.HomeFragmentListener {

    private MainMenuFragment mMainMenuFragment;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnbinder(ButterKnife.bind(this));

        mMainMenuFragment = MainMenuFragment.newInstance(this);
        setDrawerFragment(mMainMenuFragment);

        if (savedInstanceState == null) {
            showHomeFragment();
        }
    }

    protected void setFragment(BaseFragment fragment) {
        setFragment(R.id.main_container, fragment);
    }

    @Override
    public void showHomeFragment() {
        setFragment(HomeFragment.newInstance(this));
        mMainMenuFragment.setSelectedItem(MainMenuItem.HOME_TITLE);
        getDrawerLayout().closeDrawer(Gravity.START);
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
