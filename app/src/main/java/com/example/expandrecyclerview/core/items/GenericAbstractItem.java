package com.example.expandrecyclerview.core.items;

import android.support.v7.widget.RecyclerView;

import com.example.expandrecyclerview.core.IGenericItem;


/**
 * Implements the general methods of the IItem interface to speed up development.
 */
public abstract class GenericAbstractItem<Model, Item extends GenericAbstractItem<?, ?, ?>, VH extends RecyclerView.ViewHolder> extends AbstractItem<Item, VH> implements IGenericItem<Model, Item, VH> {
    private Model mModel;

    public GenericAbstractItem(Model model) {
        this.mModel = model;
    }

    public Model getModel() {
        return mModel;
    }

    @Deprecated
    public GenericAbstractItem<?, ?, ?> setModel(Model model) {
        return withModel(model);
    }

    public GenericAbstractItem<?, ?, ?> withModel(Model model) {
        this.mModel = model;
        return this;
    }
}