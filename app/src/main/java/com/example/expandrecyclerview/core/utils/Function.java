package com.example.expandrecyclerview.core.utils;

import android.support.annotation.Nullable;


public interface Function<Input, Output> {

    @Nullable
    Output apply(Input input);

}
