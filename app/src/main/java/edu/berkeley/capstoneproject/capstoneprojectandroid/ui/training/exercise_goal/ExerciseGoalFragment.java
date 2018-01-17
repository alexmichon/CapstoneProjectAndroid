package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseDialog;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalFragment extends BaseFragment<ExerciseGoalContract.View, ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor>> implements ExerciseGoalContract.View {

    @BindView(R.id.exercise_goal_recycler)
    RecyclerView mRecyclerView;

    private ExerciseGoalAdapter mRecyclerAdapter;
    private ExerciseGoalFragmentListener mListener;

    public static ExerciseGoalFragment newInstance(ExerciseGoalFragmentListener listener) {
        ExerciseGoalFragment fragment = new ExerciseGoalFragment();

        fragment.setListener(listener);

        return fragment;
    }

    public void setListener(ExerciseGoalFragmentListener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_goal, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        getPresenter().loadExerciseGoal();

        return view;
    }

    @NonNull
    @Override
    public ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseGoalPresenter();
    }

    private void setMetricGoals(List<MetricGoal> metricGoals) {
        mRecyclerAdapter = new ExerciseGoalAdapter(metricGoals);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onExerciseGoalLoading() {
        showLoading();
    }

    @Override
    public void onExerciseGoalLoaded(ExerciseGoal exerciseGoal) {
        hideLoading();
        setMetricGoals(exerciseGoal.getMetricGoals());
    }


    public interface ExerciseGoalFragmentListener {

    }
}
