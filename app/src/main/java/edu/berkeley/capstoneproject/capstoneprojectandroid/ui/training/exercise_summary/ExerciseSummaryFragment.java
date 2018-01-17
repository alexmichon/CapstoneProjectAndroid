package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalFragment;

/**
 * Created by Alex on 18/12/2017.
 */

public class ExerciseSummaryFragment extends BaseFragment<ExerciseSummaryContract.View, ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor>>
        implements ExerciseSummaryContract.View, ExerciseGoalFragment.ExerciseGoalFragmentListener {

    @BindView(R.id.exercise_summary_goal)
    FrameLayout mExerciseGoalView;

    private ExerciseSummaryFragmentListener mListener;

    private ExerciseGoalFragment mExerciseGoalDialog;


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

        getChildFragmentManager().beginTransaction()
                .replace(R.id.exercise_summary_goal, ExerciseGoalFragment.newInstance(this))
                .commit();

        return view;
    }

    @Override
    public ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseSummaryPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    public void update() {
        getPresenter().loadExerciseSummary();
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


    public void setListener(ExerciseSummaryFragmentListener listener) {
        mListener = listener;
    }

    public interface ExerciseSummaryFragmentListener {

        void onExerciseSummaryStart();
        void onExerciseSummaryBack();
    }
}
