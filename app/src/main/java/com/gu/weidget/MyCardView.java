package com.gu.weidget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.gu.DensityUtil;

public class MyCardView extends CardView {

    public MyCardView(@NonNull Context context) {
        this(context, null);
    }

    public MyCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setRadius(DensityUtil.dp2px(context, 4));
    }
}