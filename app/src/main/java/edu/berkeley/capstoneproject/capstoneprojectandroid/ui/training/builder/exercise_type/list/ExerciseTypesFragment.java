package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.single.ExerciseTypeDialog;

/**
 * Created by Alex on 17/11/2017.
 */

public class ExerciseTypesFragment extends BaseFragment<ExerciseTypesContract.View, ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor>> implements ExerciseTypesContract.View, ExerciseTypesAdapter.ExerciseTypesAdapterListener, ExerciseTypeDialog.ExerciseTypeDialogListener {

    @BindView(R.id.exercise_types_recycler)
    RecyclerView mExerciseTypesView;

    private List<ExerciseType> mExerciseTypeList;
    private ExerciseTypesAdapter mExercisesAdapter;
    private ExerciseTypesFragmentListener mListener;

    private ExerciseTypeDialog mExerciseTypeDialog;

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
    public void selectExerciseType(ExerciseType exerciseType) {
        if (mExerciseTypeDialog != null) {
            mExerciseTypeDialog.dismiss();
        }

        if (mListener != null) {
            mListener.onExerciseTypeSelect(exerciseType);
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

    @Override
    public void onExerciseTypesError(Throwable e) {
        hideLoading();
        showError(e);
    }


    @Override
    public void showExerciseTypeDialog(ExerciseType exerciseType) {
        mExerciseTypeDialog = ExerciseTypeDialog.newInstance(exerciseType, this);
        FragmentManager fragmentManager = getChildFragmentManager();
        mExerciseTypeDialog.show(fragmentManager, "ExerciseTypeDialog");
    }

    @Override
    public void dismissExerciseTypeDialog() {
        if (mExerciseTypeDialog != null) {
            mExerciseTypeDialog.dismiss();
        }
    }

    @Override
    public void onExerciseTypeMoreClick(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeMore(exerciseType);
    }

    @Override
    public void onExerciseTypeSelectClick(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeSelect(exerciseType);
    }

    @Override
    public void onExerciseTypeDialogBack() {
        getPresenter().onExerciseTypeDialogBack();
    }

    @Override
    public void onExerciseTypeDialogSelect(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeDialogSelect(exerciseType);
    }



    public void setListener(ExerciseTypesFragmentListener listener) {
        mListener = listener;
    }

    public interface ExerciseTypesFragmentListener {
        void onExerciseTypeSelect(ExerciseType exerciseType);
    }
}