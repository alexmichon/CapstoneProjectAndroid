package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.HistoryActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainActivity extends DrawerActivity<MainContract.View, MainContract.Presenter<MainContract.View, MainContract.Interactor>> implements MainContract.View {

    @BindView(R.id.main_text_hello)
    TextView mHelloView;

    @BindView(R.id.main_button_start_training)
    Button mStartTrainingButton;

    @BindView(R.id.main_button_view_results)
    Button mViewResultsButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnbinder(ButterKnife.bind(this));

        // TODO
        setNavigationDrawerMenu(R.menu.main_navigation, getPresenter().getNavigationListener());
        initNavigationDrawer();
    }


    @OnClick(R.id.main_button_start_training)
    void onStartExerciseClick() {
        getPresenter().onStartTrainingClick();
    }

    @OnClick(R.id.main_button_view_results)
    void onViewResultsClick() { getPresenter().onViewResultsClick(); }


    @Override
    public void showError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startTrainingActivity() {
        Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
        startActivity(intent);
    }

    @Override
    public void startResultsActivity() {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    @NonNull
    @Override
    public MainContract.Presenter<MainContract.View, MainContract.Interactor> createPresenter() {
        return getActivityComponent().mainPresenter();
    }
}
