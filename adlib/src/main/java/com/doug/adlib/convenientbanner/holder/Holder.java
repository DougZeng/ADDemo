/*
 * Copyright (c) 2018. doug.
 */

package com.doug.adlib.convenientbanner.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class Holder<T> extends RecyclerView.ViewHolder {
    public Holder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    protected abstract void initView(View itemView);

    public abstract void updateUI(T data);
}
