package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExerciseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_goal.ExerciseGoalFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type.ExerciseTypeFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingActivity extends ToolbarActivity<TrainingContract.View, TrainingContract.Presenter<TrainingContract.View, TrainingContract.Interactor>>
        implements TrainingContract.View, BluetoothListFragment.BluetoothListFragmentListener, ExerciseGoalFragment.ExerciseGoalFragmentListener {

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
        BluetoothListFragment fragment = new BluetoothListFragment();
        fragment.setListener(this);

        setFragment(fragment);
    }

    @Override
    public void showExerciseTypesFragment() {
        ExerciseTypeFragment fragment = new ExerciseTypeFragment();
        fragment.setListener(new ExerciseTypeFragment.ExerciseTypesFragmentListener() {
            @Override
            public void onExerciseTypeSelected(ExerciseType exerciseType) {
                showExerciseFragment(exerciseType);
            }
        });

        setFragment(fragment);
    }

    @Override
    public void showExerciseFragment(ExerciseType exerciseType) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ExerciseFragment.EXTRA_EXERCISE_TYPE, exerciseType);
        fragment.setArguments(bundle);

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
        getPresenter().onDeviceSelected(device);
    }

    @Override
    public void onExerciseGoalDone(ExerciseGoal exerciseGoal) {
        getPresenter().setExerciseGoal(exerciseGoal);
    }
}
