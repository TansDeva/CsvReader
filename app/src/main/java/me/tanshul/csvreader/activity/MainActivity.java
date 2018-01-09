package me.tanshul.csvreader.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import me.tanshul.csvreader.R;
import me.tanshul.csvreader.adapter.MainAdapter;
import me.tanshul.csvreader.databinding.MainActivityBinding;
import me.tanshul.csvreader.util.Utility;
import me.tanshul.viewmodel.DataItem;
import me.tanshul.csvreader.util.MediaItem;
import me.tanshul.viewmodel.ItemType;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public ItemType mItemType;
    private Context mContext;
    private MainAdapter mAdapter;
    private MainActivityBinding mBinding;
    private ArrayList<DataItem> mList = new ArrayList<>();
    private ArrayList<MediaItem> mItems = new ArrayList<>();
    private ArrayList<String> mListCount = Utility.getList(R.array.list_count);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mItemType = new ItemType();
        mBinding.setType(mItemType);
        mContext = this;
        getCsvContents();
        setListData();
    }

    private void setListData() {
        mAdapter = new MainAdapter(mContext, mList);
        mBinding.rvItemList.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rvItemList.setAdapter(mAdapter);
        mBinding.spItemType.setOnItemSelectedListener(this);
        mBinding.spItemCount.setOnItemSelectedListener(this);
        getAlbumList();
    }

    private void getCsvContents() {
        String fileName = "sample_music_data.csv";
        try {
            InputStream csvStream = getAssets().open(fileName);
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            List<String[]> data = csvReader.readAll();
            //Skip the header of the file
            for (int i = 1; i < data.size(); i++) {
                String[] item = data.get(i);
                mItems.add(new MediaItem(item[0], item[1], item[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAlbumList() {
        mList.clear();
        Map<String, List<DataItem.Data>> list = new HashMap<>();
        for (MediaItem item : mItems) {
            String name = item.getAlbum();
            List<DataItem.Data> data = new ArrayList<>();
            DataItem.Data value = new DataItem.Data(item.getName());
            if (list.containsKey(name)) {
                data.addAll(list.get(name));
            }
            data.add(value);
            list.put(name, data);
        }
        for (String key : list.keySet()) {
            mList.add(new DataItem(key, list.get(key)));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void getArtistList() {
        mList.clear();
        Map<String, List<DataItem.Data>> list = new HashMap<>();
        for (MediaItem item : mItems) {
            String name = item.getArtist();
            List<DataItem.Data> data = new ArrayList<>();
            DataItem.Data value = new DataItem.Data(item.getName()
            );
            if (list.containsKey(name)) {
                data.addAll(list.get(name));
            }
            data.add(value);
            list.put(name, data);
        }
        for (String key : list.keySet()) {
            mList.add(new DataItem(key, list.get(key)));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spItemType:
                if (i == 0) {
                    getAlbumList();
                } else {
                    getArtistList();
                }
                break;
            case R.id.spItemCount:
                mItemType.setCount(Integer.parseInt(mListCount.get(i)));
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Do nothing
    }
}
