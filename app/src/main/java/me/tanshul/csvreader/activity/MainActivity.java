package me.tanshul.csvreader.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.tanshul.csvreader.R;
import me.tanshul.csvreader.adapter.MainAdapter;
import me.tanshul.csvreader.model.DataItem;
import me.tanshul.csvreader.model.MediaItem;
import me.tanshul.csvreader.util.Utility;

public class MainActivity extends AppCompatActivity {
    public int mCount = 2;
    private Context mContext;
    private MainAdapter mAdapter;
    private ArrayList<DataItem> mList = new ArrayList<>();
    private ArrayList<MediaItem> mItems = new ArrayList<>();
    private String[] mListType = Utility.getStringArray(R.array.list_type);
    private String[] mListCount = Utility.getStringArray(R.array.list_count);

    //Declare all views here
    @BindView(R.id.sp_item_type)
    Spinner spItemType;
    @BindView(R.id.sp_item_count)
    Spinner spItemCount;
    @BindView(R.id.rv_item_list)
    RecyclerView rvItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        getCsvContents();
        setListData();
    }

    private void setListData() {
        mAdapter = new MainAdapter(mContext, mList);
        rvItemList.setLayoutManager(new LinearLayoutManager(mContext));
        rvItemList.setAdapter(mAdapter);
        ArrayAdapter adapterType = new ArrayAdapter(mContext,
                android.R.layout.simple_spinner_dropdown_item, mListType);
        ArrayAdapter adapterCount = new ArrayAdapter(mContext,
                android.R.layout.simple_spinner_dropdown_item, mListCount);
        spItemType.setAdapter(adapterType);
        spItemCount.setAdapter(adapterCount);
        setListeners();
        getAlbumList();
    }

    private void setListeners() {
        spItemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    getAlbumList();
                } else {
                    getArtistList();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
        spItemCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCount = Integer.parseInt(mListCount[i]);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
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
            DataItem.Data value = new DataItem.Data(item.getName(), item.getArtist());
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
            DataItem.Data value = new DataItem.Data(item.getName(), item.getAlbum());
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
}
