package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExerciseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesFragment;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingActivity extends ToolbarActivity implements TrainingContract.View {

    private static final String TAG = TrainingActivity.class.getSimpleName();

    private static final int CONTAINER_ID = R.id.training_container;

    @Inject
    TrainingContract.Presenter<TrainingContract.View, TrainingContract.Interactor> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_training);
        setUnbinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
    }

    private void setFragment(BaseFragment fragment) {
        setFragment(CONTAINER_ID, fragment);
    }


    @Override
    public void showBluetoothListFragment() {
        BluetoothListFragment fragment = new BluetoothListFragment();
        fragment.setListener(new BluetoothListFragment.BluetoothListFragmentListener() {
            @Override
            public void onDeviceConnected() {
                showExerciseTypesFragment();
            }
        });

        setFragment(fragment);
    }

    @Override
    public void showExerciseTypesFragment() {
        ExerciseTypesFragment fragment = new ExerciseTypesFragment();
        fragment.setListener(new ExerciseTypesFragment.ExerciseTypesFragmentListener() {
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
}
