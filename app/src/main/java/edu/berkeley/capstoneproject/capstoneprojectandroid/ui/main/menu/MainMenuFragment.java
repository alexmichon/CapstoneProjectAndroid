package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 15/12/2017.
 */

public class MainMenuFragment extends BaseFragment<MainMenuContract.View, MainMenuContract.Presenter<MainMenuContract.View, MainMenuContract.Interactor>> implements MainMenuContract.View, MainMenuAdapter.MainMenuAdapterListener {

    @BindView(R.id.main_menu_header_email)
    TextView mEmailView;

    @BindView(R.id.main_menu_name)
    TextView mNameView;

    @BindView(R.id.main_menu_recycler)
    RecyclerView mListView;

    private MainMenuAdapter mAdapter;
    private MainMenuItemListener mListener;


    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    public static MainMenuFragment newInstance(MainMenuItemListener listener) {
        MainMenuFragment fragment = new MainMenuFragment();
        fragment.setListener(listener);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        List<MainMenuItem> mainMenuItemList = getPresenter().getMenuItems();
        mAdapter = new MainMenuAdapter(mainMenuItemList, this);

        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void setUser(User user) {
        mEmailView.setText(user.getEmail());
        mNameView.setText(user.getFirstName() + " " + user.getLastName());
    }

    @NonNull
    @Override
    public MainMenuContract.Presenter<MainMenuContract.View, MainMenuContract.Interactor> createPresenter() {
        return getActivityComponent().mainMenuPresenter();
    }



    public void setListener(MainMenuItemListener listener) {
        mListener = listener;
    }

    @Override
    public void setSelectedItem(String title) {
        if (mAdapter != null) {
            mAdapter.setSelectedItem(title);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onMainMenuItemClick(MainMenuItem item) {
        if (mListener != null) {
            mListener.onMainMenuItemClick(item);
        }
    }

    public interface MainMenuItemListener {
        void onMainMenuItemClick(MainMenuItem item);
    }
}
