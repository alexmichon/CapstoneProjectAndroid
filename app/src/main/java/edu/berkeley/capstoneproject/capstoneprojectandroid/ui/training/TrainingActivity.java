package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.ExerciseBuilderFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise.ExerciseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result.ExerciseResultFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result.TrainingExerciseResultFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary.ExerciseSummaryFragment;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingActivity extends ToolbarActivity<TrainingContract.View, TrainingContract.Presenter<TrainingContract.View, TrainingContract.Interactor>>
        implements TrainingContract.View,
        BluetoothListFragment.BluetoothListFragmentListener,
        ExerciseBuilderFragment.ExerciseBuilderFragmentListener,
        ExerciseSummaryFragment.ExerciseSummaryFragmentListener,
        ExerciseFragment.ExerciseFragmentListener,
        TrainingExerciseResultFragment.TrainingExerciseResultFragmentListener {

    private static final int CONTAINER_ID = R.id.training_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        setUnbinder(ButterKnife.bind(this));

        if (savedInstanceState == null) {
            showBluetoothListFragment();
        }
    }

    @NonNull
    @Override
    public TrainingContract.Presenter<TrainingContract.View, TrainingContract.Interactor> createPresenter() {
        return getActivityComponent().trainingPresenter();
    }

    private void setFragment(BaseFragment fragment) {
        setFragment(CONTAINER_ID, fragment);
    }



    @Override
    public void showExerciseBuilderFragment() {
        ExerciseBuilderFragment fragment = ExerciseBuilderFragment.newInstance(this);
        setFragment(fragment);
    }


    @Override
    public void showBluetoothListFragment() {
        // TODO User newInstance
        BluetoothListFragment fragment = new BluetoothListFragment();
        fragment.setListener(this);

        setFragment(fragment);
        setTitle("Bluetooth device");
    }

    @Override
    public void showExerciseSummaryFragment() {
        ExerciseSummaryFragment fragment = ExerciseSummaryFragment.newInstance(this);
        setFragment(fragment);
        setTitle("Summary");
    }

    @Override
    public void showExerciseFragment() {
        ExerciseFragment fragment = ExerciseFragment.newInstance(this);
        setFragment(fragment);
        setTitle("Training");
    }

    @Override
    public void showExerciseResultFragment() {
        TrainingExerciseResultFragment fragment = TrainingExerciseResultFragment.newInstance(this);
        setFragment(fragment);
        setTitle("Results");
    }

    @Override
    public void moveToMainActivity() {
        Intent intent = new Intent(TrainingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBluetoothDeviceSelected() {
        getPresenter().onDeviceSelect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }

    @Override
    public void onExerciseSummaryStart() {
        getPresenter().onExerciseSummaryStart();
    }

    @Override
    public void onExerciseSummaryBack() {
        getPresenter().onExerciseSummaryBack();
    }

    @Override
    public void onExerciseDone() {
        getPresenter().onExerciseDone();
    }

    @Override
    public void onExerciseResultMenu() {
        getPresenter().onExerciseResultMenu();
    }

    @Override
    public void onExerciseBuilt() {
        getPresenter().onExerciseBuilt();
    }
}
