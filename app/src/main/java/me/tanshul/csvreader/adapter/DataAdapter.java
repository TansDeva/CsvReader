package me.tanshul.csvreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.tanshul.csvreader.activity.MainActivity;
import me.tanshul.csvreader.databinding.ItemDataBinding;
import me.tanshul.viewmodel.DataItem;

/**
 * Created by tansdeva on 28/12/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context mContext;
    private MainActivity mActivity;
    private LayoutInflater mInflater;
    private List<DataItem.Data> mItemList;

    public DataAdapter(Context context, List<DataItem.Data> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        this.mActivity = (MainActivity) mContext;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        ItemDataBinding binding = ItemDataBinding.inflate(mInflater, parent, false);
        return new DataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        DataItem.Data item = mItemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return Math.min(mActivity.mItemType.getCount(), mItemList.size());
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        private ItemDataBinding mBinding;

        DataViewHolder(ItemDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        private void bind(DataItem.Data data) {
            mBinding.setData(data);
        }
    }
}
