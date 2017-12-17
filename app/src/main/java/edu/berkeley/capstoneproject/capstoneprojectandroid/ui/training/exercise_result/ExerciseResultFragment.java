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

    private static final String KEY_EXERCISE = "KEY_EXERCISE";

    private Exercise mExercise;
    private ExerciseResult mExerciseResult;
    private ExerciseResultAdapter mAdapter;

    private ExerciseGoalFragment.ExerciseGoalFragmentListener mListener;

    public static ExerciseGoalFragment newInstance(Exercise exercise, ExerciseGoalFragment.ExerciseGoalFragmentListener listener) {
        ExerciseGoalFragment fragment = new ExerciseGoalFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_EXERCISE, exercise);
        fragment.setArguments(args);

        fragment.setListener(listener);

        return fragment;
    }

    public void setListener(ExerciseGoalFragment.ExerciseGoalFragmentListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mExerciseResult = args.getParcelable(KEY_EXERCISE);
        if (mExerciseResult == null) {
            Timber.e("Exercise goal can't be null");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_goal, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public void onExerciseResultLoaded(ExerciseResult exerciseResult) {
        mAdapter = new ExerciseResultAdapter(exerciseResult);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
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
}
