package me.tanshul.csvreader.util;

import android.content.Context;

import me.tanshul.csvreader.CsvReaderApp;

/**
 * Created by tansdeva on 28/12/17.
 */

public class Utility {
    private static Context mContext = CsvReaderApp.getContext();

    public static String[] getStringArray(int resId) {
        return mContext.getResources().getStringArray(resId);
    }
}
