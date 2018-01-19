package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalFragment extends BaseFragment<ExerciseGoalContract.View, ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor>> implements ExerciseGoalContract.View {

    private static final String EXERCISE_GOAL_KEY = "EXERCISE_GOAL_KEY";

    @BindView(R.id.exercise_goal_recycler)
    RecyclerView mRecyclerView;

    private ExerciseGoalAdapter mRecyclerAdapter;
    private ExerciseGoalFragmentListener mListener;

    public static ExerciseGoalFragment newInstance(ExerciseGoal exerciseGoal, ExerciseGoalFragmentListener listener) {
        ExerciseGoalFragment fragment = new ExerciseGoalFragment();

        Bundle args = new Bundle();
        args.putParcelable(EXERCISE_GOAL_KEY, exerciseGoal);
        fragment.setArguments(args);

        fragment.setListener(listener);

        return fragment;
    }

    public void setListener(ExerciseGoalFragmentListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        ExerciseGoal exerciseGoal = bundle.getParcelable(EXERCISE_GOAL_KEY);
        if (exerciseGoal == null) {
            Timber.e("Exercise Goal can't be null");
        }

        getPresenter().setExerciseGoal(exerciseGoal);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_goal, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadExerciseGoalInfo();
    }

    @NonNull
    @Override
    public ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseGoalPresenter();
    }

    @Override
    public void setMetricGoals(List<MetricGoal> metricGoals) {
        mRecyclerAdapter = new ExerciseGoalAdapter(metricGoals);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.notifyDataSetChanged();
    }


    public interface ExerciseGoalFragmentListener {

    }
}
