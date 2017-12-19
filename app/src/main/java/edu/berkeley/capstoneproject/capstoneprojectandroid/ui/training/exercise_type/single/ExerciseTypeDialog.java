package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseDialog;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.AppConstants;
import timber.log.Timber;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeDialog extends BaseDialog<ExerciseTypeContract.View, ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor>>
        implements ExerciseTypeContract.View, YouTubePlayer.OnInitializedListener {

    public static final String EXERCISE_TYPE_KEY = "ExerciseType";

    private ExerciseType mExerciseType;
    private ExerciseTypeDialogListener mListener;

    @BindView(R.id.dialog_exercise_type_name)
    TextView mNameView;

    @BindView(R.id.dialog_exercise_type_description)
    TextView mDescriptionView;

    @BindView(R.id.dialog_exercise_type_video)
    VideoView mVideoView;

    @BindView(R.id.dialog_exercise_type_youtube)
    FrameLayout mYoutubeLayout;

    @BindView(R.id.dialog_exercise_type_back_button)
    Button mBackButton;

    @BindView(R.id.dialog_exercise_type_start_button)
    Button mStartButton;


    public static ExerciseTypeDialog newInstance(ExerciseType exerciseType, ExerciseTypeDialogListener listener) {
        ExerciseTypeDialog dialog = new ExerciseTypeDialog();

        Bundle args = new Bundle();
        args.putParcelable(EXERCISE_TYPE_KEY, exerciseType);
        dialog.setArguments(args);

        dialog.setListener(listener);

        return dialog;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mExerciseType = bundle.getParcelable(EXERCISE_TYPE_KEY);
        if (mExerciseType == null) {
            Timber.e("Exercise Type can't be null");
        }

        getPresenter().setExerciseType(mExerciseType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exercise_type, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setUnbinder(ButterKnife.bind(this, view));

        mNameView.setText(mExerciseType.getName());
        mDescriptionView.setText(mExerciseType.getDescription());

        Uri videoUri = mExerciseType.getVideoUri();
        if (videoUri != null) {
            mVideoView.setVideoURI(videoUri);
            mVideoView.setVisibility(View.VISIBLE);

            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
            mVideoView.requestFocus();
            mVideoView.start();
        }
        else {
            mVideoView.setVisibility(View.GONE);
        }

        YouTubePlayerSupportFragment fragment = YouTubePlayerSupportFragment.newInstance();
        String youtubeId = mExerciseType.getYoutubeVideo();
        if (youtubeId != null) {
            mYoutubeLayout.setVisibility(View.VISIBLE);
            fragment.initialize(AppConstants.YOUTUBE_API_KEY, this);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.dialog_exercise_type_youtube, fragment)
                    .commit();
        }
        else {
            mYoutubeLayout.setVisibility(View.GONE);
        }

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onExerciseTypeSelect();
            }
        });

        return view;
    }

    @NonNull
    @Override
    public ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseTypePresenter();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (youTubePlayer == null) {
            return;
        }

        if (!wasRestored) {
            youTubePlayer.cueVideo(mExerciseType.getYoutubeVideo());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        youTubeInitializationResult.getErrorDialog(getActivity(), 0).show();
    }

    public void setListener(ExerciseTypeDialogListener listener) {
        mListener = listener;
    }

    @Override
    public void onExerciseTypeSelect(ExerciseType exerciseType) {
        if (mListener != null) {
            mListener.onExerciseTypeDialogSelect(exerciseType);
        }
    }

    public interface ExerciseTypeDialogListener {
        void onExerciseTypeDialogBack();
        void onExerciseTypeDialogSelect(ExerciseType exerciseType);
    }
}
