package com.example.expandrecyclerview.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expandrecyclerview.R;
import com.example.expandrecyclerview.core.FastAdapter;
import com.example.expandrecyclerview.core.IAdapter;
import com.example.expandrecyclerview.core.IExpandable;
import com.example.expandrecyclerview.core.IItem;
import com.example.expandrecyclerview.core.ISubItem;
import com.example.expandrecyclerview.epdlib.items.AbstractExpandableItem;
import com.example.expandrecyclerview.model.ItemModel;

import java.util.List;


/**
 * Created by mikepenz on 28.12.15.
 */
public class SimpleExpandableHeaderItem<Parent extends IItem & IExpandable, SubItem extends IItem & ISubItem> extends AbstractExpandableItem<SimpleExpandableHeaderItem<Parent, SubItem>, SimpleExpandableHeaderItem.ViewHolder, SubItem> {

    public interface listener{
       void   expandableListener(boolean expand);
    }

    private FastAdapter.OnClickListener<SimpleExpandableHeaderItem> mOnClickListener;


    SimpleExpandableHeaderItem.listener mListener;

    private boolean expand;
    private boolean isLineTopVisible;
    private boolean isLineBottomVisible;
    private ItemModel mHeader;

    public SimpleExpandableHeaderItem<Parent, SubItem> withExpand(boolean expand) {
        this.expand = expand;
        return this;
    }

    public SimpleExpandableHeaderItem<Parent, SubItem> withHeader(ItemModel header) {
        this.mHeader = header;
        return this;
    }

    public SimpleExpandableHeaderItem<Parent, SubItem> withVisibleLineTop(boolean isVisible) {
        this.isLineTopVisible = isVisible;
        return this;
    }

    public SimpleExpandableHeaderItem<Parent, SubItem> withVisibleLineBottom(boolean isVisible) {
        this.isLineBottomVisible = isVisible;
        return this;
    }


    public SimpleExpandableHeaderItem<Parent, SubItem> withListener(SimpleExpandableHeaderItem.listener listener) {
        this.mListener = listener;
        return this;
    }


    public FastAdapter.OnClickListener<SimpleExpandableHeaderItem> getOnClickListener() {
        return mOnClickListener;
    }

    public SimpleExpandableHeaderItem<Parent, SubItem> withOnClickListener(FastAdapter.OnClickListener<SimpleExpandableHeaderItem> mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    //we define a clickListener in here so we can directly animate
    final private FastAdapter.OnClickListener<SimpleExpandableHeaderItem<Parent, SubItem>> onClickListener = new FastAdapter.OnClickListener<SimpleExpandableHeaderItem<Parent, SubItem>>() {
        @Override
        public boolean onClick(View v, IAdapter adapter, SimpleExpandableHeaderItem item, int position) {
            if (item.getSubItems() != null) {
                if (!item.isExpanded()) {
                    ViewCompat.animate(v.findViewById(R.id.ivArrow)).rotation(180).start();
                    expand = true;
                } else {
                    ViewCompat.animate(v.findViewById(R.id.ivArrow)).rotation(0).start();
                    expand = false;
                }

                if(mListener != null){
                    mListener.expandableListener(item.isExpanded());
                }

                return mOnClickListener == null || mOnClickListener.onClick(v, adapter, item, position);
            }
            return mOnClickListener != null && mOnClickListener.onClick(v, adapter, item, position);
        }
    };

    /**
     * we overwrite the item specific click listener so we can automatically animate within the item
     *
     * @return
     */
    @Override
    public FastAdapter.OnClickListener<SimpleExpandableHeaderItem<Parent, SubItem>> getOnItemClickListener() {
        return onClickListener;
    }

    @Override
    public boolean isSelectable() {
        //this might not be true for your application
        return getSubItems() == null;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.expand_adapter_header_id;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.recycler_expand_header_item;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);
        viewHolder.tvDate.setText("22/10");
        viewHolder.tvDesc.setText(mHeader.getDesc());
        viewHolder.ivArrow.setRotation(isExpanded() ? 180 : 0);
        viewHolder.ivCheck.setImageResource(R.drawable.ic_simple_check_green);
        viewHolder.lineTop.setVisibility(isLineTopVisible ? View.VISIBLE: View.INVISIBLE);
        viewHolder.lineBottomExpandableItem.setVisibility(isLineBottomVisible ? View.VISIBLE: View.INVISIBLE);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.tvDate.setText(null);
        holder.tvDesc.setText(null);
        holder.ivArrow.setRotation(isExpanded() ? 180 : 0);
        holder.ivCheck.setImageResource(0);

        holder.lineTop.setVisibility(isLineTopVisible ? View.VISIBLE: View.INVISIBLE);
        holder.lineBottomExpandableItem.setVisibility(isLineBottomVisible ? View.VISIBLE: View.INVISIBLE);

    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    @Override
    public boolean isExpanded() {
        return expand;
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llClickExpandable;
        private View lineTop;
        private ImageView ivCheck;
        private View lineBottomExpandableItem;
        private TextView tvDate;
        private TextView tvDesc;
        private ImageView ivArrow;

        public ViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View v) {
            llClickExpandable = (LinearLayout) v.findViewById(R.id.llClickExpandable);
            lineTop = (View) v.findViewById(R.id.lineTop);
            ivCheck = (ImageView) v.findViewById(R.id.ivCheck);
            lineBottomExpandableItem = (View) v.findViewById(R.id.lineBottomExpandableItem);
            tvDate = (TextView) v.findViewById(R.id.tvDate);
            tvDesc = (TextView) v.findViewById(R.id.tvDesc);
            ivArrow = (ImageView) v.findViewById(R.id.ivArrow);
        }

    }
}
