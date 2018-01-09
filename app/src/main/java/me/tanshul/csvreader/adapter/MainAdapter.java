package me.tanshul.csvreader.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.tanshul.csvreader.activity.MainActivity;
import me.tanshul.csvreader.databinding.MainDataBinding;
import me.tanshul.csvreader.util.Utility;
import me.tanshul.viewmodel.DataItem;

/**
 * Created by tansdeva on 28/12/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private Context mContext;
    private MainActivity mActivity;
    private LayoutInflater mInflater;
    private ArrayList<DataItem> mItemList;

    public MainAdapter(Context context, ArrayList<DataItem> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        this.mActivity = (MainActivity) mContext;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        MainDataBinding binding = MainDataBinding.inflate(mInflater, parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        DataItem item = mItemList.get(position);
        DataAdapter adapter = new DataAdapter(mContext, item.getItems());
        int size = Math.min(item.getItems().size(), mActivity.mItemType.getCount());
        GridLayoutManager glManager = new GridLayoutManager(mContext,
                size, GridLayoutManager.HORIZONTAL, false);
        holder.mBinding.rvItemData.setLayoutManager(glManager);
        holder.mBinding.rvItemData.setHasFixedSize(true);
        holder.mBinding.rvItemData.setAdapter(adapter);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        private MainDataBinding mBinding;

        MainViewHolder(MainDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(DataItem item) {
            this.mBinding.setData(item);
        }
    }
}
