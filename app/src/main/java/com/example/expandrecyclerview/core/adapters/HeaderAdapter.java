package com.example.expandrecyclerview.core.adapters;


import com.example.expandrecyclerview.core.IItem;

/**
 * Based on the ItemAdapter, with a different order to show its items before the ItemAdapter
 */
public class HeaderAdapter<Item extends IItem> extends ItemAdapter<Item> {
    /**
     * @return the order
     */
    @Override
    public int getOrder() {
        return 100;
    }

}
