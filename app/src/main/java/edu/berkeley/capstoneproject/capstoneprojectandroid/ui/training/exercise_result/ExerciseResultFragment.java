package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal.ExerciseGoalFragment;
import timber.log.Timber;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultFragment extends BaseFragment<ExerciseResultContract.View, ExerciseResultContract.Presenter<ExerciseResultContract.View, ExerciseResultContract.Interactor>> implements ExerciseResultContract.View {

    @BindView(R.id.exercise_result_recycler)
    RecyclerView mRecyclerView;

    private Exercise mExercise;
    private ExerciseResult mExerciseResult;
    private ExerciseResultAdapter mAdapter;

    private ExerciseResultFragment.ExerciseResultFragmentListener mListener;

    public static ExerciseResultFragment newInstance(ExerciseResultFragment.ExerciseResultFragmentListener listener) {
        ExerciseResultFragment fragment = new ExerciseResultFragment();

        fragment.setListener(listener);

        return fragment;
    }

    public void setListener(ExerciseResultFragment.ExerciseResultFragmentListener listener) {
        mListener = listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_goal, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        getPresenter().loadExerciseResult();

        return view;
    }

    @Override
    public void onExerciseResultLoaded(ExerciseResult exerciseResult) {
        hideLoading();
        mAdapter = new ExerciseResultAdapter(exerciseResult);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
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

    @OnClick(R.id.exercise_goal_ok)
    public void onClick(View v) {
        getPresenter().onOkClick(mExerciseResult);
    }

    @NonNull
    @Override
    public ExerciseResultContract.Presenter<ExerciseResultContract.View, ExerciseResultContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseResultPresenter();
    }

    public interface ExerciseResultFragmentListener {
    }
}
