package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeFragment extends BaseFragment<ExerciseTypeContract.View, ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor>> implements ExerciseTypeContract.View {

    public static final String EXERCISE_TYPE_KEY = "ExerciseType";

    private ExerciseType mExerciseType;

    @BindView(R.id.exercise_type_description)
    TextView mDescriptionView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_type, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        Bundle bundle = getArguments();
        mExerciseType = bundle.getParcelable(EXERCISE_TYPE_KEY);
        if (mExerciseType == null) {
            Timber.e("Exercise Type can't be null");
            return null;
        }

        mDescriptionView.setText(mExerciseType.getDescription());

        return view;
    }

    @NonNull
    @Override
    public ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseTypePresenter();
    }
}
