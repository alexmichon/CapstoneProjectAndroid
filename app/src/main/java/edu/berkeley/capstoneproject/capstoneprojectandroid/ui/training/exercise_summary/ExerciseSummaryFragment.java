package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 18/12/2017.
 */

public class ExerciseSummaryFragment extends BaseFragment<ExerciseSummaryContract.View, ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor>> implements ExerciseSummaryContract.View {

    @BindView(R.id.exercise_summary_title)
    TextView mTitleView;

    private ExerciseSummaryFragmentListener mListener;


    public static ExerciseSummaryFragment newInstance(ExerciseSummaryFragmentListener listener) {
        ExerciseSummaryFragment fragment = new ExerciseSummaryFragment();
        fragment.setListener(listener);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_summary, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public ExerciseSummaryContract.Presenter<ExerciseSummaryContract.View, ExerciseSummaryContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseSummaryPresenter();
    }




    @OnClick(R.id.exercise_summary_start)
    public void onStartClick(View v) {
        getPresenter().onStartClick();
    }



    public void setListener(ExerciseSummaryFragmentListener listener) {
        mListener = listener;
    }

    @Override
    public void onStartExercise() {
        if (mListener != null) {
            mListener.onExerciseSummaryStart();
        }
    }

    public interface ExerciseSummaryFragmentListener {

        void onExerciseSummaryStart();
    }
}
