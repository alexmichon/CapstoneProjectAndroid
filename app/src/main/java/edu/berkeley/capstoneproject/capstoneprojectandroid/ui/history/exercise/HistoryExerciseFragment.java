package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result.ExerciseResultFragment;
import timber.log.Timber;

/**
 * Created by Alex on 08/12/2017.
 */

public class HistoryExerciseFragment extends BaseFragment<HistoryExerciseContract.View, HistoryExerciseContract.Presenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor>>
    implements HistoryExerciseContract.View {

    private static final String KEY_EXERCISE = "KEY_EXERCISE";

    private DateFormat mDateFormat;


    @BindView(R.id.history_exercise_name)
    TextView mNameView;

    @BindView(R.id.history_exercise_date)
    TextView mDateView;


    public static HistoryExerciseFragment newInstance(Exercise exercise) {
        HistoryExerciseFragment fragment = new HistoryExerciseFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_EXERCISE, exercise);
        fragment.setArguments(args);

        return fragment;
    }


    @NonNull
    @Override
    public HistoryExerciseContract.Presenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor> createPresenter() {
        return getActivityComponent().historyExercisePresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        Exercise exercise = args.getParcelable(KEY_EXERCISE);

        if (exercise == null) {
            Timber.e("Exercise can't be null");
        }

        getPresenter().setExercise(exercise);

        mDateFormat = DateFormat.getDateTimeInstance();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_exercise, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadExerciseInfo();
        getPresenter().loadExerciseResult();
    }

    @Override
    public void onExerciseResultError(Throwable throwable) {
        hideLoading();
        showError(throwable);
    }

    @Override
    public void onExerciseResultLoading() {
        showLoading("Please wait while we process your results...");
    }

    @Override
    public void onExerciseLoaded(Exercise exercise) {
        mNameView.setText(exercise.getName());
        mDateView.setText(mDateFormat.format(exercise.getStartDate()));
    }

    @Override
    public void onExerciseResultLoaded(ExerciseResult exerciseResult) {
        hideLoading();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.history_exercise_result_container, ExerciseResultFragment.newInstance(exerciseResult))
                .commit();
    }
}
