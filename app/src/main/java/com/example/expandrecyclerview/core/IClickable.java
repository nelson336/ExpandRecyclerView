package com.example.expandrecyclerview.core;



public interface IClickable<Item extends IItem> {
    Item withOnItemPreClickListener(FastAdapter.OnClickListener<Item> onItemPreClickListener);

    FastAdapter.OnClickListener<Item> getOnPreItemClickListener();

    Item withOnItemClickListener(FastAdapter.OnClickListener<Item> onItemClickListener);

    FastAdapter.OnClickListener<Item> getOnItemClickListener();
}
