package com.example.expandrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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


public class SimpleExpandItemAdapter<Parent extends IItem & IExpandable & ISubItem & IClickable> extends AbstractExpandableItem<Parent, SimpleExpandItemAdapter.ViewHolder, SimpleExpandItemAdapter<Parent>> implements IDraggable<SimpleExpandItemAdapter, IItem> {

    public interface Listener{
        void clickButton(int pos, ItemSubModel item);
    }

    private ItemSubModel mItem;
    private boolean mIsDraggable = true;

    private Listener mListener;

    public SimpleExpandItemAdapter withItem(ItemSubModel expandItem){
        this.mItem = expandItem;
        return this;
    }

    public SimpleExpandItemAdapter withListener(SimpleExpandItemAdapter.Listener listener){
        this.mListener  = listener;
        return this;
    }

    @Override
    public boolean isDraggable() {
        return mIsDraggable;
    }

    @Override
    public SimpleExpandItemAdapter withIsDraggable(boolean draggable) {
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
        return R.id.expand_adpter_item_id;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.recycler_expand_item;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    @Override
    public void bindView(final ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        //get the context
        Context ctx = viewHolder.itemView.getContext();
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.clickButton(viewHolder.getAdapterPosition(), mItem);
                }
            }
        });
        viewHolder.ivCheck.setImageResource(R.drawable.ic_simple_check_green);
        viewHolder.tvDocumentName.setText(mItem.getSubDesc());

    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.button.setOnClickListener(null);
        holder.ivCheck.setImageResource(0);
        holder.tvDocumentName.setText(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCheck;
        private TextView tvDocumentName;
        private Button button;
        private View line;

        public ViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View v) {
            ivCheck = (ImageView) v.findViewById(R.id.ivCheck);
            tvDocumentName = (TextView) v.findViewById(R.id.tvDocumentName);
            button = (Button) v.findViewById(R.id.button);
            line = (View) v.findViewById(R.id.line);
        }
    }
}
