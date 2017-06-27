package com.example.expandrecyclerview.core.adapters;


import com.example.expandrecyclerview.core.IItem;

/**
 * Based on the ItemAdapter, with a different order to show its items after the ItemAdapter
 */
public class FooterAdapter<Item extends IItem> extends ItemAdapter<Item> {

    /**
     * @return the order
     */
    @Override
    public int getOrder() {
        return 1000;
    }

}
