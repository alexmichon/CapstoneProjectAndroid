package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExerciseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list.ExerciseTypesFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single.ExerciseTypeDialog;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single.ExerciseTypeFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingActivity extends ToolbarActivity<TrainingContract.View, TrainingContract.Presenter<TrainingContract.View, TrainingContract.Interactor>> implements TrainingContract.View {

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


    private void showBluetoothListFragment() {
        BluetoothListFragment fragment = new BluetoothListFragment();
        fragment.setListener(new BluetoothListFragment.BluetoothListFragmentListener() {
            @Override
            public void onDeviceSelected(Rx2BleDevice device) {
                getPresenter().onDeviceSelected(device);
            }
        });

        setFragment(fragment);
        setTitle("Bluetooth device");
    }

    private void showExerciseTypesFragment() {
        ExerciseTypesFragment fragment = new ExerciseTypesFragment();
        fragment.setListener(new ExerciseTypesFragment.ExerciseTypesFragmentListener() {
            @Override
            public void onExerciseTypeStart(ExerciseType exerciseType) {
                getPresenter().onExerciseTypeStart(exerciseType);
            }

            @Override
            public void onExerciseTypeMore(ExerciseType exerciseType) {
                showExerciseTypeDialog(exerciseType);
            }
        });

        setFragment(fragment);
        setTitle("Exercise types");
    }

    private void showExerciseTypeDialog(ExerciseType exerciseType) {
        ExerciseTypeDialog fragment = new ExerciseTypeDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ExerciseTypeFragment.EXERCISE_TYPE_KEY, exerciseType);
        fragment.setArguments(bundle);
        fragment.setListener(new ExerciseTypeDialog.ExerciseTypeDialogListener() {
            @Override
            public void onExerciseTypeStart(ExerciseType exerciseType) {
                getPresenter().onExerciseTypeStart(exerciseType);
            }
        });

        showDialog(fragment);
    }

    private void showExerciseFragment(ExerciseType exerciseType) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ExerciseFragment.EXTRA_EXERCISE_TYPE, exerciseType);
        fragment.setArguments(bundle);

        setFragment(fragment);
        setTitle(exerciseType.getName());
    }

    @Override
    public void onDeviceConnected() {
        hideLoading();
        showMessage("Device validated");
        showExerciseTypesFragment();
    }

    @Override
    public void startExerciseType(ExerciseType exerciseType) {
        showExerciseFragment(exerciseType);
    }
}
