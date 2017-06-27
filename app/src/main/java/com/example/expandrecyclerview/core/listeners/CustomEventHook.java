package com.example.expandrecyclerview.core.listeners;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.expandrecyclerview.R;
import com.example.expandrecyclerview.core.FastAdapter;
import com.example.expandrecyclerview.core.IItem;

import java.util.List;

public abstract class CustomEventHook<Item extends IItem> implements EventHook<Item> {
    /**
     * this method is called by the `FastAdapter` during ViewHolder creation ONCE.
     *
     * @param view
     * @param viewHolder
     */
    public abstract void attachEvent(View view, RecyclerView.ViewHolder viewHolder);

    /**
     * Helper method to get the FastAdapter from this ViewHolder
     *
     * @param viewHolder
     * @return
     */
    @Nullable
    public FastAdapter<Item> getFastAdapter(RecyclerView.ViewHolder viewHolder) {
        Object tag = viewHolder.itemView.getTag(R.id.fastadapter_item_adapter);
        if (tag instanceof FastAdapter) {
            return (FastAdapter<Item>) tag;
        }
        return null;
    }

    /**
     * helper method to get the item for this ViewHolder
     *
     * @param viewHolder
     * @return
     */
    @Nullable
    public Item getItem(RecyclerView.ViewHolder viewHolder) {
        FastAdapter<Item> adapter = getFastAdapter(viewHolder);
        if (adapter == null) {
            return null;
        }
        //we get the adapterPosition from the viewHolder
        int pos = adapter.getHolderAdapterPosition(viewHolder);
        //make sure the click was done on a valid item
        if (pos != RecyclerView.NO_POSITION) {
            //we update our item with the changed property
            Object tag = viewHolder.itemView.getTag(R.id.fastadapter_item);
            if (tag instanceof IItem) {
                return (Item) tag;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
        return null;
    }

    @Nullable
    @Override
    public List<View> onBindMany(@NonNull RecyclerView.ViewHolder viewHolder) {
        return null;
    }
}