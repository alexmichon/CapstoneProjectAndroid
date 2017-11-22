package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.content.Intent;
import android.os.Bundle;
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
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainActivity extends DrawerActivity implements MainContract.View {



    @Inject
    MainContract.Presenter<MainContract.View, MainContract.Interactor> mPresenter;

    @BindView(R.id.main_text_hello)
    TextView mHelloView;

    @BindView(R.id.main_button_start_training)
    Button mStartTrainingButton;

    @BindView(R.id.main_button_view_results)
    Button mViewResultsButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main2);
        setUnbinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

        // TODO
        setNavigationDrawerMenu(R.menu.main_navigation, mPresenter.getNavigationListener());
        initNavigationDrawer();
    }


    @OnClick(R.id.main_button_start_training)
    void onStartExerciseClick() {
        mPresenter.onStartTrainingClick();
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

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
