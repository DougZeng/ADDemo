/*
 * Copyright (c) 2018. doug.
 */

package com.doug.adlib.anim;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CountAnimationUtil extends Animation {
    private OnInterpolatedTimeChanged listener;

    public CountAnimationUtil() {
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (listener != null) {
            listener.onInterpolatedTimeChanged(interpolatedTime);
        }
    }

    public interface OnInterpolatedTimeChanged {
        void onInterpolatedTimeChanged(float interpolatedTime);
    }

    public void setInterpolatedTimeChangeListener(OnInterpolatedTimeChanged listener) {
        this.listener = listener;

    }
}
