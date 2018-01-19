package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result;

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
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultFragment extends BaseFragment<ExerciseResultContract.View, ExerciseResultContract.Presenter<ExerciseResultContract.View, ExerciseResultContract.Interactor>> implements ExerciseResultContract.View {

    private static final String KEY_EXERCISE_RESULT = "KEY_EXERCISE_RESULT";

    @BindView(R.id.exercise_result_recycler)
    RecyclerView mRecyclerView;

    private ExerciseResultAdapter mAdapter;

    public static ExerciseResultFragment newInstance(ExerciseResult exerciseResult) {
        ExerciseResultFragment fragment = new ExerciseResultFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_EXERCISE_RESULT, exerciseResult);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        ExerciseResult exerciseResult = bundle.getParcelable(KEY_EXERCISE_RESULT);
        if (exerciseResult == null) {
            Timber.e("Exercise Result can't be null");
        }

        getPresenter().setExerciseResult(exerciseResult);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.component_exercise_result, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadExerciseResultInfo();
    }

    @Override
    public void onExerciseResultLoaded(ExerciseResult exerciseResult) {
        mAdapter = new ExerciseResultAdapter(exerciseResult);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @NonNull
    @Override
    public ExerciseResultContract.Presenter<ExerciseResultContract.View, ExerciseResultContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseResultPresenter();
    }


}
