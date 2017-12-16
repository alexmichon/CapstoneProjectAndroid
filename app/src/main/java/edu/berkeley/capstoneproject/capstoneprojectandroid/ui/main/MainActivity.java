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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainActivity extends ToolbarActivity<MainContract.View, MainContract.Presenter<MainContract.View, MainContract.Interactor>> implements MainContract.View {

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.main_text_hello)
    TextView mHelloView;

    @BindView(R.id.main_button_start_training)
    Button mStartTrainingButton;

    @BindView(R.id.main_button_view_results)
    Button mViewResultsButton;

    private ActionBarDrawerToggle drawerToggle;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnbinder(ButterKnife.bind(this));

        // TODO
        //setNavigationDrawerMenu(R.menu.main_navigation, getPresenter().getNavigationListener());
        //initNavigationDrawer();

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @OnClick(R.id.main_button_start_training)
    void onStartExerciseClick() {
        getPresenter().onStartTrainingClick();
    }


    @Override
    public void showError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startTrainingActivity() {
        Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
        startActivity(intent);
    }

    @NonNull
    @Override
    public MainContract.Presenter<MainContract.View, MainContract.Interactor> createPresenter() {
        return getActivityComponent().mainPresenter();
    }
}
