package com.example.expandrecyclerview.core.listeners;

import android.support.annotation.Nullable;


import com.example.expandrecyclerview.core.IItem;

import java.util.List;

/**
 * interface for the ItemFilterListener
 */
public interface ItemFilterListener<Item extends IItem> {
    void itemsFiltered(@Nullable CharSequence constraint, @Nullable List<Item> results);

    void onReset();
}