package me.tanshul.viewmodel;

import java.util.ArrayList;

import me.tanshul.csvreader.R;
import me.tanshul.csvreader.util.Utility;

/**
 * Created by tansdeva on 6/1/18.
 */

public class ItemType {
    private boolean isArtist = false;
    private int count = Utility.DEFAULT_COUNT;
    private ArrayList<String> mListType = Utility.getList(R.array.list_type);
    private ArrayList<String> mListCount = Utility.getList(R.array.list_count);

    public boolean isArtist() {
        return isArtist;
    }

    public void setArtist(boolean artist) {
        isArtist = artist;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<String> getListType() {
        return mListType;
    }

    public ArrayList<String> getListCount() {
        return mListCount;
    }
}
