package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 17/11/2017.
 */

public class ExerciseTypesFragment extends BaseFragment<ExerciseTypesContract.View, ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor>> implements ExerciseTypesContract.View, ExerciseTypesAdapter.ExerciseTypesAdapterListener {

    @BindView(R.id.exercise_types_recycler)
    RecyclerView mExerciseTypesView;

    private List<ExerciseType> mExerciseTypeList;
    private ExerciseTypesAdapter mExercisesAdapter;
    private ExerciseTypesFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_types, container, false);

        ButterKnife.bind(this, view);

        mExerciseTypeList = new ArrayList<>();
        mExercisesAdapter = new ExerciseTypesAdapter(mExerciseTypeList, this);
        mExerciseTypesView.setLayoutManager(new LinearLayoutManager(getContext()));
        mExerciseTypesView.setAdapter(mExercisesAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onLoadExerciseTypes();
    }

    @NonNull
    @Override
    public ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseTypesPresenter();
    }

    @Override
    public void addExerciseType(ExerciseType exerciseType) {
        mExerciseTypeList.add(exerciseType);
        mExercisesAdapter.notifyDataSetChanged();
    }

    @Override
    public void startExerciseTypeActivity(ExerciseType exerciseType) {
        // TODO
    }

    @Override
    public void onExerciseTypeMore(ExerciseType exerciseType) {
        if (mListener != null) {
            mListener.onExerciseTypeMore(exerciseType);
        }
    }

    @Override
    public void onExerciseTypeStart(ExerciseType exerciseType) {
        if (mListener != null) {
            mListener.onExerciseTypeStart(exerciseType);
        }
    }

    @Override
    public void onExerciseTypesLoading() {
        showLoading();
    }

    @Override
    public void onExerciseTypesDoneLoading() {
        hideLoading();
    }


    public void setListener(ExerciseTypesFragmentListener listener) {
        mListener = listener;
    }

    @Override
    public void onMoreClick(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeMoreClick(exerciseType);
    }

    @Override
    public void onStartClick(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeStartClick(exerciseType);
    }

    public interface ExerciseTypesFragmentListener {
        void onExerciseTypeStart(ExerciseType exerciseType);
        void onExerciseTypeMore(ExerciseType exerciseType);
    }
}