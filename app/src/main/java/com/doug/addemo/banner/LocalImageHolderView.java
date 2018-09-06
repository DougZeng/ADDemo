/*
 * Copyright (c) 2018. doug.
 */

package com.doug.addemo.banner;

import android.view.View;
import android.widget.ImageView;

import com.doug.addemo.R;
import com.doug.adlib.convenientbanner.holder.Holder;

public class LocalImageHolderView extends Holder<Integer> {
    private ImageView imageView;

    public LocalImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView =itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setImageResource(data);
    }
}
