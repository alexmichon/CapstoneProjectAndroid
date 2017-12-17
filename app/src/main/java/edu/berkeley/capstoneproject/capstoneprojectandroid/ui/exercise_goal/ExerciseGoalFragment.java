package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_goal;

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
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalFragment extends BaseFragment<ExerciseGoalContract.View, ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor>> implements ExerciseGoalContract.View {

    private static final String KEY_EXERCISE_GOAL = "KEY_EXERCISE_GOAL";

    private ExerciseGoal mExerciseGoal;
    private ExerciseGoalAdapter mAdapter;

    private ExerciseGoalFragmentListener mListener;

    @BindView(R.id.exercise_goal_recycler)
    RecyclerView mRecyclerView;

    public static ExerciseGoalFragment newInstance(ExerciseGoal exerciseGoal, ExerciseGoalFragmentListener listener) {
        ExerciseGoalFragment fragment = new ExerciseGoalFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_EXERCISE_GOAL, exerciseGoal);
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

        Bundle args = getArguments();
        mExerciseGoal = args.getParcelable(KEY_EXERCISE_GOAL);
        if (mExerciseGoal == null) {
            Timber.e("Exercise goal can't be null");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_goal, container, false);
        setUnbinder(ButterKnife.bind(this, view));


        mAdapter = new ExerciseGoalAdapter(mExerciseGoal);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @OnClick(R.id.exercise_goal_ok)
    public void onClick(View v) {
        getPresenter().onOkClick(mExerciseGoal);
    }

    @Override
    public String getTitle() {
        return null;
    }

    @NonNull
    @Override
    public ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseGoalPresenter();
    }

    @Override
    public void onDone(ExerciseGoal exerciseGoal) {
        if (mListener != null) {
            mListener.onExerciseGoalDone(exerciseGoal);
        }
    }

    public interface ExerciseGoalFragmentListener {
        void onExerciseGoalDone(ExerciseGoal exerciseGoal);
    }
}
