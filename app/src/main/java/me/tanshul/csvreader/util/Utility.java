package me.tanshul.csvreader.util;

import android.content.Context;

import java.util.ArrayList;

import me.tanshul.csvreader.CsvReaderApp;

/**
 * Created by tansdeva on 28/12/17.
 */

public class Utility {
    public static final int DEFAULT_COUNT = 1;
    private static Context mContext = CsvReaderApp.getContext();

    public static String[] getStringArray(int resId) {
        return mContext.getResources().getStringArray(resId);
    }

    public static ArrayList<String> getList(int resId) {
        ArrayList<String> list = new ArrayList<>();
        String[] data = Utility.getStringArray(resId);
        for (String item : data) {
            list.add(item);
        }
        return list;
    }
}
