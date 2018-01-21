package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryExercisesFragment extends BaseFragment<HistoryExercisesContract.View, HistoryExercisesContract.Presenter<HistoryExercisesContract.View, HistoryExercisesContract.Interactor>>
        implements HistoryExercisesContract.View,
        HistoryExercisesAdapter.HistoryExercisesAdapterListener{

    @BindView(R.id.history_exercises_recycler)
    RecyclerView mExercisesView;

    private List<Exercise> mExerciseList;
    private HistoryExercisesAdapter mHistoryExercisesAdapter;

    private HistoryExercisesAdapter mExercisesAdapter;

    private HistoryExercisesFragmentListener mListener;


    public static HistoryExercisesFragment newInstance(HistoryExercisesFragmentListener listener) {
        HistoryExercisesFragment fragment = new HistoryExercisesFragment();
        fragment.setListener(listener);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_exercises, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        mExerciseList = new ArrayList<>();

        mExercisesAdapter = new HistoryExercisesAdapter(mExerciseList);
        mExercisesAdapter.setListener(this);

        mExercisesView.setLayoutManager(new LinearLayoutManager(getContext()));
        mExercisesView.setAdapter(mExercisesAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().loadExercises();
    }

    @NonNull
    @Override
    public HistoryExercisesContract.Presenter<HistoryExercisesContract.View, HistoryExercisesContract.Interactor> createPresenter() {
        return getActivityComponent().historyExercisesPresenter();
    }

    @Override
    public void addExercise(Exercise exercise) {
        mExerciseList.add(exercise);
        mExercisesAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectHistoryExercise(Exercise exercise) {
        if (mListener != null) {
            mListener.onHistoryExerciseSelect(exercise);
        }
    }

    @Override
    public void onLoadingExercises(OnCancelListener cancelListener) {
        showLoading("Loading exercises...", cancelListener);
    }

    @Override
    public void onExercisesLoaded() {
        hideLoading();
    }

    @Override
    public void onHistoryExerciseSelect(Exercise exercise) {
        getPresenter().onHistoryExerciseSelect(exercise);
    }


    public void setListener(HistoryExercisesFragmentListener listener) {
        mListener = listener;
    }

    public interface HistoryExercisesFragmentListener {
        void onHistoryExerciseSelect(Exercise exercise);
    }
}
