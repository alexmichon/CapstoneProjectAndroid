package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.AppConstants;
import timber.log.Timber;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeFragment extends BaseFragment<ExerciseTypeContract.View, ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor>> implements ExerciseTypeContract.View, YouTubePlayer.OnInitializedListener {

    public static final String EXERCISE_TYPE_KEY = "ExerciseType";

    @BindView(R.id.exercise_type_description)
    TextView mDescriptionView;

    @BindView(R.id.exercise_type_video_layout)
    LinearLayout mVideoLayout;

    YouTubePlayerSupportFragment mYoutubeFragment;
    String mYoutubeUrl;

    private ExerciseTypeFragmentListener mListener;

    public static ExerciseTypeFragment newInstance(ExerciseType exerciseType, ExerciseTypeFragmentListener listener) {
        ExerciseTypeFragment fragment = new ExerciseTypeFragment();

        Bundle args = new Bundle();
        args.putParcelable(EXERCISE_TYPE_KEY, exerciseType);
        fragment.setArguments(args);

        fragment.setListener(listener);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        ExerciseType exerciseType = bundle.getParcelable(EXERCISE_TYPE_KEY);
        if (exerciseType == null) {
            Timber.e("Exercise Type can't be null");
        }

        getPresenter().setExerciseType(exerciseType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_type, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        mYoutubeFragment = YouTubePlayerSupportFragment.newInstance();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadExerciseTypeInfo();
    }

    @NonNull
    @Override
    public ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseTypePresenter();
    }


    public void setListener(ExerciseTypeFragmentListener listener) {
        mListener = listener;
    }

    @Override
    public void setYoutubeVideo(String url) {
        mYoutubeUrl = url;
        if (mYoutubeUrl != null) {
            mYoutubeFragment.initialize(AppConstants.YOUTUBE_API_KEY, this);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.exercise_type_youtube_frame, mYoutubeFragment)
                    .commit();

            mVideoLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setDescription(String description) {
        mDescriptionView.setText(description);
    }

    public interface ExerciseTypeFragmentListener {

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (youTubePlayer == null) {
            return;
        }

        if (!wasRestored) {
            youTubePlayer.cueVideo(mYoutubeUrl);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        youTubeInitializationResult.getErrorDialog(getActivity(), 0).show();
    }
}
