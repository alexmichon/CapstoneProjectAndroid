package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result.ExerciseResultFragment;

/**
 * Created by Alex on 18/01/2018.
 */

public class TrainingExerciseResultFragment extends BaseFragment<TrainingExerciseResultContract.View, TrainingExerciseResultContract.Presenter<TrainingExerciseResultContract.View, TrainingExerciseResultContract.Interactor>>
    implements TrainingExerciseResultContract.View {

    @BindView(R.id.training_exercise_result_title)
    TextView mTitleView;

    private TrainingExerciseResultFragmentListener mListener;

    public static TrainingExerciseResultFragment newInstance(TrainingExerciseResultFragmentListener listener) {
        TrainingExerciseResultFragment fragment = new TrainingExerciseResultFragment();

        fragment.setListener(listener);

        return fragment;
    }

    public void setListener(TrainingExerciseResultFragment.TrainingExerciseResultFragmentListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public TrainingExerciseResultContract.Presenter<TrainingExerciseResultContract.View, TrainingExerciseResultContract.Interactor> createPresenter() {
        return getActivityComponent().trainingExerciseResultPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_exercise_result, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadExercise();
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
    public void onExerciseResultLoaded(ExerciseResult exerciseResult) {
        hideLoading();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.training_exercise_result_container, ExerciseResultFragment.newInstance(exerciseResult))
                .commit();
    }


    @Override
    public void onExerciseLoaded(Exercise exercise) {
        mTitleView.setText(exercise.getName());
    }


    @OnClick(R.id.training_exercise_result_menu)
    public void onMenuClick(View v) {
        getPresenter().onMenuClick();
    }

    @Override
    public void goToMenu() {
        if (mListener != null) {
            mListener.onExerciseResultMenu();
        }
    }

    public interface TrainingExerciseResultFragmentListener {
        void onExerciseResultMenu();
    }
}
