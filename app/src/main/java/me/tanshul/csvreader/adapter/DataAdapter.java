package me.tanshul.csvreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.tanshul.csvreader.R;
import me.tanshul.csvreader.activity.MainActivity;
import me.tanshul.csvreader.model.DataItem;

/**
 * Created by tansdeva on 28/12/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context mContext;
    private MainActivity mActivity;
    private List<DataItem.Data> mItemList;

    public DataAdapter(Context context, List<DataItem.Data> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        this.mActivity = (MainActivity) mContext;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_list_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        DataItem.Data item = mItemList.get(position);
        holder.tvItemName.setText(item.getName());
        holder.tvItemData.setText(item.getData());
    }

    @Override
    public int getItemCount() {
        return Math.min(mActivity.mCount, mItemList.size());
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_name)
        TextView tvItemName;
        @BindView(R.id.tv_item_data)
        TextView tvItemData;

        DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
