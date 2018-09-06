/*
 * Copyright (c) 2018. doug.
 */

package com.doug.adlib.convenientbanner.holder;

import android.view.View;

public interface CBViewHolderCreator {
    Holder createHolder(View itemView);
    int getLayoutId();
}
