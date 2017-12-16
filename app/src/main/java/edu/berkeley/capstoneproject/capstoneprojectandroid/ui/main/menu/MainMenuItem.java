package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu;

/**
 * Created by Alex on 15/12/2017.
 */

public class MainMenuItem {

    private String mTitle;
    private int mIcon;

    public MainMenuItem(String title, int icon) {
        mTitle = title;
        mIcon = icon;
    }

    public int getIcon() {
        return mIcon;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
