package com.example.expandrecyclerview.core.utils;

import android.support.annotation.NonNull;


import com.example.expandrecyclerview.core.IIdentifyable;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class IdDistributor {
    private static final AtomicLong idDistributor = new AtomicLong(9000000000000000000L);

    /**
     * set an unique identifier for all items which do not have one set already
     *
     * @param items
     * @return
     */
    public static <T extends IIdentifyable> List<T> checkIds(@NonNull List<T> items) {
        for (int i = 0, size = items.size(); i < size; i++) {
            checkId(items.get(i));
        }
        return items;
    }

    /**
     * set an unique identifier for all items which do not have one set already
     *
     * @param items
     * @return
     */
    public static <T extends IIdentifyable> T[] checkIds(@NonNull T... items) {
        for (T item : items) {
            checkId(item);
        }
        return items;
    }

    /**
     * set an unique identifier for the item which do not have one set already
     *
     * @param item
     * @return
     */
    public static <T extends IIdentifyable> T checkId(@NonNull T item) {
        if (item.getIdentifier() == -1) {
            item.withIdentifier(idDistributor.incrementAndGet());
        }
        return item;
    }
}
