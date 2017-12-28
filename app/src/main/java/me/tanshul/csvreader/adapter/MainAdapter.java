package me.tanshul.csvreader.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.tanshul.csvreader.R;
import me.tanshul.csvreader.model.DataItem;

/**
 * Created by tansdeva on 28/12/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private Context mContext;
    private ArrayList<DataItem> mItemList;

    public MainAdapter(Context context, ArrayList<DataItem> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_list_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        DataItem item = mItemList.get(position);
        holder.tvItemTitle.setText(item.getTitle());
        DataAdapter adapter = new DataAdapter(mContext, item.getItems());
        holder.rvItemData.setLayoutManager(new LinearLayoutManager(mContext));
        holder.rvItemData.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_title)
        TextView tvItemTitle;
        @BindView(R.id.rv_item_data)
        RecyclerView rvItemData;

        MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
