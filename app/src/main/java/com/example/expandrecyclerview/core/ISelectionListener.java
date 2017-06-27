package com.example.expandrecyclerview.core;




public interface ISelectionListener<Item extends IItem> {
    /**
     * is called, whenever the provided item is selected or deselected
     *
     * @param item the item who's selection state changed
     *             param selected the new selection state of the item
     */
    void onSelectionChanged(Item item, boolean selected);
}
