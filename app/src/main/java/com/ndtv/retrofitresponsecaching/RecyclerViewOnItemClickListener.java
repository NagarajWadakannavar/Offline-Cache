package com.ndtv.retrofitresponsecaching;

import android.view.View;

/**
 * This class propagates the click events from adapter item to the
 * host fragment
 */
public class RecyclerViewOnItemClickListener<T> {

    private final T item;
    private final Listener listener;
    private final int position;

    public RecyclerViewOnItemClickListener(T item, int position, Listener listener) {
        this.item = item;
        this.position = position;
        this.listener = listener;
    }

    public void onClick(View view) {
        listener.onItemClick(item, view, position);
    }

    public interface Listener<T> {
        /**
         * Called when a item is clicked.
         */
        void onItemClick(T item, View v, int position);
    }

}
