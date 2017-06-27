package com.example.expandrecyclerview.epdlib.utils;

import android.support.annotation.Nullable;

import com.example.expandrecyclerview.core.IItem;


public class DiffCallbackImpl<Item extends IItem> implements DiffCallback<Item> {
    @Override
    public boolean areItemsTheSame(Item oldItem, Item newItem) {
        return oldItem.getIdentifier() == newItem.getIdentifier();
    }

    @Override
    public boolean areContentsTheSame(Item oldItem, Item newItem) {
        return oldItem.equals(newItem);
    }

    @Nullable
    @Override
    public Object getChangePayload(Item oldItem, int oldItemPosition, Item newItem, int newItemPosition) {
        return null;
    }
}
