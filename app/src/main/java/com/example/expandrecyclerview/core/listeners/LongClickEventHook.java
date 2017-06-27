package com.example.expandrecyclerview.core.listeners;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.expandrecyclerview.core.FastAdapter;
import com.example.expandrecyclerview.core.IItem;

import java.util.List;

public abstract class LongClickEventHook<Item extends IItem> implements EventHook<Item> {
    public abstract boolean onLongClick(View v, int position, FastAdapter<Item> fastAdapter, Item item);

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