package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.EncoderMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.ImuMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.imu.ImuFragment;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseActivity extends ToolbarActivity implements ExerciseContract.View {

    private static final String TAG = ExerciseActivity.class.getSimpleName();

    public static final String EXTRA_EXERCISE_TYPE = "ExerciseType";


    private ExerciseType mExerciseType;
    private Exercise mExercise;

    private boolean mStarted;

    @Inject
    ExerciseContract.Presenter<ExerciseContract.View, ExerciseContract.Interactor> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ButterKnife.bind(this);

        Bundle data = getIntent().getExtras();
        mExerciseType = (ExerciseType) data.getParcelable(EXTRA_EXERCISE_TYPE);
        if (mExerciseType == null) {
            Log.e(TAG, "Exercise type can't be null");
        }

        mPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onExerciseStart() {

    }

    @Override
    public void onExerciseStop() {

    }

    @Override
    public void addEncoderValue(Measurement measurement) {

    }

    @Override
    public void addAccMeasurement(Measurement measurement) {

    }

    @Override
    public void addGyrMeasurement(Measurement measurement) {

    }
}
