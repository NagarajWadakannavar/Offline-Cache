package com.ndtv.retrofitresponsecaching;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ELAA on 25-10-2016.
 */

public class RecyclerViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final T binding;

    public RecyclerViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public Context getContext() {
        return binding.getRoot().getContext();
    }

    public T getBinding() {
        return binding;
    }
}


