package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import timber.log.Timber;

/**
 * Created by Alex on 15/12/2017.
 */

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {

    private final List<MainMenuItem> mMainMenuItems;
    private MainMenuAdapterListener mListener;
    private String mSelectedItem;

    public MainMenuAdapter(List<MainMenuItem> mainMenuItems) {
        mMainMenuItems = mainMenuItems;
    }

    public MainMenuAdapter(List<MainMenuItem> mainMenuItems, MainMenuAdapterListener listener) {
        mMainMenuItems = mainMenuItems;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main_menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainMenuItem mainMenuItem = mMainMenuItems.get(position);
        holder.bind(mainMenuItem);
    }

    @Override
    public int getItemCount() {
        return mMainMenuItems.size();
    }

    public void setSelectedItem(MainMenuItem item) {
        mSelectedItem = item.getTitle();
    }

    public void setSelectedItem(String title) {
        mSelectedItem = title;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.main_menu_item_title)
        TextView mTitleView;

        @BindView(R.id.main_menu_item_icon)
        ImageView mIconView;

        MainMenuItem mMainMenuItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MainMenuItem mainMenuItem) {
            mMainMenuItem = mainMenuItem;

            if (mainMenuItem.getTitle().equals(mSelectedItem)) {
                itemView.setBackgroundResource(R.color.colorLight);
            }

            mTitleView.setText(mainMenuItem.getTitle());
            mIconView.setImageResource(mainMenuItem.getIcon());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onMainMenuItemClick(mMainMenuItem);
                    }
                }
            });
        }
    }

    public interface MainMenuAdapterListener {
        void onMainMenuItemClick(MainMenuItem item);
    }
}
