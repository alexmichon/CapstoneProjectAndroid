package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type.ExerciseTypeFragment;

/**
 * Created by Alex on 18/12/2017.
 */

public class ExerciseSummaryFragment extends BaseFragment<ExerciseSummaryContract.View, ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor>>
        implements ExerciseSummaryContract.View, ExerciseGoalFragment.ExerciseGoalFragmentListener, ExerciseTypeFragment.ExerciseTypeFragmentListener {

    @BindView(R.id.exercise_summary_title)
    TextView mTitleView;

    @BindView(R.id.exercise_summary_type)
    FrameLayout mExerciseTypeView;

    @BindView(R.id.exercise_summary_goal)
    FrameLayout mExerciseGoalView;

    private ExerciseSummaryFragmentListener mListener;


    public static ExerciseSummaryFragment newInstance(ExerciseSummaryFragmentListener listener) {
        ExerciseSummaryFragment fragment = new ExerciseSummaryFragment();
        fragment.setListener(listener);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_summary, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadExerciseType();
        getPresenter().loadExerciseGoal();
    }

    @Override
    public ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseSummaryPresenter();
    }

    @OnClick(R.id.exercise_summary_start)
    public void onStartClick(View v) {
        getPresenter().onStartClick();
    }

    @OnClick(R.id.exercise_summary_back)
    public void onBackClick(View v) {
        getPresenter().onBackClick();
    }


    @Override
    public void startExercise() {
        if (mListener != null) {
            mListener.onExerciseSummaryStart();
        }
    }

    @Override
    public void moveBack() {
        if (mListener != null) {
            mListener.onExerciseSummaryBack();
        }
    }

    @Override
    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    @Override
    public void showExerciseType(ExerciseType exerciseType) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.exercise_summary_type, ExerciseTypeFragment.newInstance(exerciseType, this))
                .commit();
    }

    @Override
    public void showExerciseGoal(ExerciseGoal exerciseGoal) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.exercise_summary_goal, ExerciseGoalFragment.newInstance(exerciseGoal, this))
                .commit();
    }


    public void setListener(ExerciseSummaryFragmentListener listener) {
        mListener = listener;
    }

    public interface ExerciseSummaryFragmentListener {

        void onExerciseSummaryStart();
        void onExerciseSummaryBack();
    }
}
