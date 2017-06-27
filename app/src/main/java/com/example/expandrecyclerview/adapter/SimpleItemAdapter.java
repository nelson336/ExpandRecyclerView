package com.example.expandrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expandrecyclerview.R;
import com.example.expandrecyclerview.core.IClickable;
import com.example.expandrecyclerview.core.IDraggable;
import com.example.expandrecyclerview.core.IExpandable;
import com.example.expandrecyclerview.core.IItem;
import com.example.expandrecyclerview.core.ISubItem;
import com.example.expandrecyclerview.epdlib.items.AbstractExpandableItem;
import com.example.expandrecyclerview.model.ItemModel;
import com.example.expandrecyclerview.model.ItemSubModel;

import java.util.List;

/**
 * Created by nelson336 on 25/06/2017.
 */

public class SimpleItemAdapter<Parent extends IItem & IExpandable & ISubItem & IClickable> extends AbstractExpandableItem<Parent, SimpleItemAdapter.ViewHolder, SimpleItemAdapter<Parent>> implements IDraggable<SimpleItemAdapter, IItem> {

    private ItemModel mItem;
    private boolean mIsDraggable = true;

    public SimpleItemAdapter withItem(ItemModel item) {
        this.mItem = item;
        return this;
    }


    @Override
    public boolean isDraggable() {
        return mIsDraggable;
    }

    @Override
    public SimpleItemAdapter withIsDraggable(boolean draggable) {
        this.mIsDraggable = draggable;
        return this;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.adapter_item_id;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.recycler_item;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    @Override
    public void bindView(final ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);
        viewHolder.ivCheck.setImageResource(R.drawable.ic_simple_check_green);
        viewHolder.tvDesc.setText(mItem.getDesc());
        viewHolder.tvDate.setText("25/10");
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.ivCheck.setImageResource(0);
        holder.tvDesc.setText(null);
        holder.tvDate.setText(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutBasic;
        private View lineTop;
        private ImageView ivCheck;
        private View lineBottom;
        private TextView tvDate;
        private TextView tvDesc;
        private View lineDivider;


        public ViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View v) {
            layoutBasic = (LinearLayout) v.findViewById(R.id.layoutBasic);
            lineTop = (View) v.findViewById(R.id.lineTop);
            ivCheck = (ImageView) v.findViewById(R.id.ivCheck);
            lineBottom = (View) v.findViewById(R.id.lineBottom);
            tvDate = (TextView) v.findViewById(R.id.tvDate);
            tvDesc = (TextView) v.findViewById(R.id.tvDesc);
            lineDivider = (View) v.findViewById(R.id.lineDivider);
        }
    }
}