package com.ndtv.retrofitresponsecaching;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ELAA on 25-10-2016.
 * Modified by Nagaraj
 */

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<NewsItem> list;
    private final RecyclerViewOnItemClickListener.Listener listener;


    public NewsListAdapter(Context context, List<NewsItem> list, RecyclerViewOnItemClickListener.Listener listener) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_news_list_item, parent, false);
        return new RecyclerViewHolder<>(binding);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            NewsItem item = list.get(position);
            RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
            viewHolder.getBinding().setVariable(BR.news, item);
            viewHolder.getBinding().setVariable(BR.clickListener, new RecyclerViewOnItemClickListener<>(item, position, listener));

            viewHolder.getBinding().executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        NewsItem item = list.get(position);
        return item.type;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
