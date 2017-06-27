package com.example.expandrecyclerview.epdlib.items;

import android.support.v7.widget.RecyclerView;


import com.example.expandrecyclerview.core.IClickable;
import com.example.expandrecyclerview.core.IExpandable;
import com.example.expandrecyclerview.core.IItem;
import com.example.expandrecyclerview.core.ISubItem;
import com.example.expandrecyclerview.core.items.AbstractItem;

import java.util.List;


public abstract class AbstractExpandableItem<Parent extends IItem & IExpandable & ISubItem & IClickable, VH extends RecyclerView.ViewHolder, SubItem extends IItem & ISubItem> extends AbstractItem<Parent, VH> implements IExpandable<AbstractExpandableItem, SubItem>, ISubItem<AbstractExpandableItem, Parent> {
    private List<SubItem> mSubItems;
    private Parent mParent;
    private boolean mExpanded = false;

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public AbstractExpandableItem<Parent, VH, SubItem> withIsExpanded(boolean expanded) {
        mExpanded = expanded;
        return this;
    }

    @Override
    public List<SubItem> getSubItems() {
        return mSubItems;
    }

    @Override
    public boolean isAutoExpanding() {
        return true;
    }

    @Override
    public AbstractExpandableItem<Parent, VH, SubItem> withSubItems(List<SubItem> subItems) {
        this.mSubItems = subItems;
        for (SubItem subItem : subItems) {
            subItem.withParent(this);
        }
        return this;
    }

    @Override
    public Parent getParent() {
        return mParent;
    }

    @Override
    public AbstractExpandableItem<Parent, VH, SubItem> withParent(Parent parent) {
        this.mParent = parent;
        return this;
    }

    @Override
    public boolean isSelectable() {
        //this might not be true for your application
        return getSubItems() == null;
    }
}
