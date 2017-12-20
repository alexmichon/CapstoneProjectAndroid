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

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalDialog extends BaseDialog<ExerciseGoalContract.View, ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor>> implements ExerciseGoalContract.View {

    @BindView(R.id.exercise_goal_type)
    Spinner mTypeView;
    ArrayAdapter<String> mTypeAdapter;

    @BindView(R.id.exercise_goal_recycler)
    RecyclerView mRecyclerView;

    private ExerciseGoalAdapter mRecyclerAdapter;
    private ExerciseGoalFragmentListener mListener;

    private List<MetricGoal> mMetricGoals = new ArrayList<>();

    public static ExerciseGoalDialog newInstance(ExerciseGoalFragmentListener listener) {
        ExerciseGoalDialog fragment = new ExerciseGoalDialog();

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

        mTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ExerciseGoal.Type.nameList());
        mTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeView.setAdapter(mTypeAdapter);
        mTypeView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ExerciseGoal.Type type = ExerciseGoal.Type.find(adapterView.getItemAtPosition(i).toString());
                updateExerciseGoalType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getPresenter().loadCurrentExerciseGoal();

        return view;
    }

    @Override
    public void onCurrentExerciseGoalLoaded(ExerciseGoal exerciseGoal) {
        mRecyclerAdapter = new ExerciseGoalAdapter(exerciseGoal.getMetricGoals());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.notifyDataSetChanged();

        setExerciseGoalType(exerciseGoal.getType());
    }

    @Override
    public void onDefaultExerciseGoalLoaded(ExerciseGoal exerciseGoal) {
        if (getExerciseGoalType() == ExerciseGoal.Type.DEFAULT) {
            mRecyclerAdapter = new ExerciseGoalAdapter(exerciseGoal.getMetricGoals());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onExerciseGoalEditDone() {
        if (mListener != null) {
            mListener.onExerciseGoalEditDone();
        }
    }

    @OnClick(R.id.exercise_goal_ok)
    public void onOkClick(View v) {
        getPresenter().onSaveExerciseGoal();
    }

    @NonNull
    @Override
    public ExerciseGoalContract.Presenter<ExerciseGoalContract.View, ExerciseGoalContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseGoalPresenter();
    }




    @Override
    public void setExerciseGoalType(ExerciseGoal.Type type) {
        int position = mTypeAdapter.getPosition(type.getName());
        mTypeView.setSelection(position);
    }

    public void updateExerciseGoalType(ExerciseGoal.Type type) {
        switch (type) {
            case NONE:
                mRecyclerView.setVisibility(View.GONE);
                break;
            case CUSTOM:
                mRecyclerView.setVisibility(View.VISIBLE);
                setEditable(true);
                break;
            case DEFAULT:
                getPresenter().loadDefaultExerciseGoal();
                mRecyclerView.setVisibility(View.VISIBLE);
                setEditable(false);
                break;
        }
    }

    protected void setEditable(boolean editable) {
        mRecyclerAdapter.setEditable(editable);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public ExerciseGoal.Type getExerciseGoalType() {
        int position = mTypeView.getSelectedItemPosition();
        return ExerciseGoal.Type.find(mTypeAdapter.getItem(position));
    }

    @Override
    public List<MetricGoal> getMetricGoals() {
        return mRecyclerAdapter.getMetricGoals();
    }

    @Override
    public void setMetricGoals(List<MetricGoal> metricGoals) {
        mRecyclerAdapter = new ExerciseGoalAdapter(metricGoals);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.notifyDataSetChanged();
    }


    public interface ExerciseGoalFragmentListener {
        void onExerciseGoalEditDone();
    }
}
