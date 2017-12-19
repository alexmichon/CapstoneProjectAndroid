package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise.ExerciseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result.ExerciseResultFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary.ExerciseSummaryFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list.ExerciseTypesFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single.ExerciseTypeDialog;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single.ExerciseTypeFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingActivity extends ToolbarActivity<TrainingContract.View, TrainingContract.Presenter<TrainingContract.View, TrainingContract.Interactor>>
        implements TrainingContract.View,
        BluetoothListFragment.BluetoothListFragmentListener,
        ExerciseTypesFragment.ExerciseTypesFragmentListener,
        ExerciseTypeDialog.ExerciseTypeDialogListener,
        ExerciseGoalFragment.ExerciseGoalFragmentListener,
        ExerciseSummaryFragment.ExerciseSummaryFragmentListener,
        ExerciseFragment.ExerciseFragmentListener, ExerciseResultFragment.ExerciseResultFragmentListener {

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
    public void showBluetoothListFragment() {
        // TODO User newInstance
        BluetoothListFragment fragment = new BluetoothListFragment();
        fragment.setListener(this);

        setFragment(fragment);
        setTitle("Bluetooth device");
    }

    @Override
    public void showExerciseTypesFragment() {
        // TODO User newInstance
        ExerciseTypesFragment fragment = new ExerciseTypesFragment();
        fragment.setListener(this);

        setFragment(fragment);
        setTitle("Exercise types");
    }

    @Override
    public void showExerciseTypeDialog(ExerciseType exerciseType) {
        // TODO User newInstance
        ExerciseTypeDialog fragment = new ExerciseTypeDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ExerciseTypeFragment.EXERCISE_TYPE_KEY, exerciseType);
        fragment.setArguments(bundle);
        fragment.setListener(this);
        showDialog(fragment);
    }

    @Override
    public void showExerciseGoalFragment() {
        ExerciseGoalFragment fragment = ExerciseGoalFragment.newInstance(this);
        setFragment(fragment);
    }

    @Override
    public void showExerciseSummaryFragment() {
        ExerciseSummaryFragment fragment = ExerciseSummaryFragment.newInstance(this);
        setFragment(fragment);
    }

    @Override
    public void showExerciseFragment() {
        ExerciseFragment fragment = ExerciseFragment.newInstance(this);
        setFragment(fragment);
    }

    @Override
    public void showExerciseResultFragment() {
        ExerciseResultFragment fragment = ExerciseResultFragment.newInstance(this);
        setFragment(fragment);
    }

    @Override
    public void onDeviceConnected() {
        hideLoading();
        showMessage("Device validated");
        showExerciseTypesFragment();
    }

    @Override
    public void onBluetoothDeviceSelected(Rx2BleDevice device) {
        getPresenter().onDeviceSelect(device);
    }

    @Override
    public void onExerciseGoalDone(ExerciseGoal exerciseGoal) {
        getPresenter().onExerciseGoalSelect(exerciseGoal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }

    @Override
    public void onExerciseTypeSelect(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeSelect(exerciseType);
    }

    @Override
    public void onExerciseTypeMore(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeMore(exerciseType);
    }

    @Override
    public void onExerciseSummaryStart() {
        getPresenter().onExerciseSummaryStart();
    }

    @Override
    public void onExerciseDone() {
        getPresenter().onExerciseDone();
    }
}
