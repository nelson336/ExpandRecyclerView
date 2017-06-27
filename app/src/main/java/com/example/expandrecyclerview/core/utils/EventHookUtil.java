package com.example.expandrecyclerview.core.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.expandrecyclerview.R;
import com.example.expandrecyclerview.core.FastAdapter;
import com.example.expandrecyclerview.core.IItem;
import com.example.expandrecyclerview.core.listeners.ClickEventHook;
import com.example.expandrecyclerview.core.listeners.CustomEventHook;
import com.example.expandrecyclerview.core.listeners.EventHook;
import com.example.expandrecyclerview.core.listeners.LongClickEventHook;
import com.example.expandrecyclerview.core.listeners.TouchEventHook;

import java.util.Arrays;
import java.util.List;

public class EventHookUtil {
    /**
     * binds the hooks to the viewHolder
     *
     * @param viewHolder the viewHolder of the item
     */
    public static <Item extends IItem> void bind(@NonNull final RecyclerView.ViewHolder viewHolder, @Nullable final List<EventHook<Item>> eventHooks) {
        if (eventHooks == null) {
            return;
        }
        for (final EventHook<Item> event : eventHooks) {
            View view = event.onBind(viewHolder);
            if (view != null) {
                attachToView(event, viewHolder, view);
            }

            List<? extends View> views = event.onBindMany(viewHolder);
            if (views != null) {
                for (View v : views) {
                    attachToView(event, viewHolder, v);
                }
            }
        }
    }

    /**
     * attaches the specific event to a view
     *
     * @param event      the event to attach
     * @param viewHolder the viewHolder containing this view
     * @param view       the view to attach to
     */
    public static <Item extends IItem> void attachToView(@NonNull final EventHook<Item> event, @NonNull final RecyclerView.ViewHolder viewHolder, @NonNull final View view) {
        if (event instanceof ClickEventHook) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get the adapter for this view
                    Object tagItem = viewHolder.itemView.getTag(R.id.fastadapter_item);
                    Object tagAdapter = viewHolder.itemView.getTag(R.id.fastadapter_item_adapter);
                    if (tagItem instanceof IItem && tagAdapter instanceof FastAdapter) {
                        Item item = (Item) tagItem;
                        FastAdapter<Item> adapter = (FastAdapter<Item>) tagAdapter;
                        //we get the adapterPosition from the viewHolder
                        int pos = adapter.getHolderAdapterPosition(viewHolder);
                        //make sure the click was done on a valid item
                        if (pos != RecyclerView.NO_POSITION) {
                            //we update our item with the changed property
                            ((ClickEventHook<Item>) event).onClick(v, pos, adapter, item);
                        }
                    }
                }
            });
        } else if (event instanceof LongClickEventHook) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //get the adapter for this view
                    Object tagItem = viewHolder.itemView.getTag(R.id.fastadapter_item);
                    Object tagAdapter = viewHolder.itemView.getTag(R.id.fastadapter_item_adapter);
                    if (tagItem instanceof IItem && tagAdapter instanceof FastAdapter) {
                        Item item = (Item) tagItem;
                        FastAdapter<Item> adapter = (FastAdapter<Item>) tagAdapter;
                        //we get the adapterPosition from the viewHolder
                        int pos = adapter.getHolderAdapterPosition(viewHolder);
                        //make sure the click was done on a valid item
                        if (pos != RecyclerView.NO_POSITION) {
                            //we update our item with the changed property
                            return ((LongClickEventHook<Item>) event).onLongClick(v, pos, adapter, item);
                        }
                    }
                    return false;
                }
            });
        } else if (event instanceof TouchEventHook) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent e) {
                    //get the adapter for this view
                    Object tagItem = viewHolder.itemView.getTag(R.id.fastadapter_item);
                    Object tagAdapter = viewHolder.itemView.getTag(R.id.fastadapter_item_adapter);
                    if (tagItem instanceof IItem && tagAdapter instanceof FastAdapter) {
                        Item item = (Item) tagItem;
                        FastAdapter<Item> adapter = (FastAdapter<Item>) tagAdapter;
                        //we get the adapterPosition from the viewHolder
                        int pos = adapter.getHolderAdapterPosition(viewHolder);
                        //make sure the click was done on a valid item
                        if (pos != RecyclerView.NO_POSITION) {
                            //we update our item with the changed property
                            return ((TouchEventHook<Item>) event).onTouch(v, e, pos, adapter, item);
                        }
                    }
                    return false;
                }
            });
        } else if (event instanceof CustomEventHook) {
            //we trigger the event binding
            ((CustomEventHook<Item>) event).attachEvent(view, viewHolder);
        }
    }

    /**
     * a small helper to get a list of views from a dynamic amout of views.
     *
     * @param views the views to get as list
     * @return the list with the views
     */
    public static List<View> toList(View... views) {
        return Arrays.asList(views);
    }
}
