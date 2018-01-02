package com.gu;

import android.content.Context;

public class DensityUtil {

    public static int dp2px(Context context, float dp) {
        return (int) ((context.getResources().getDisplayMetrics().density * dp) + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        return (int) (pxValue /  context.getResources().getDisplayMetrics().density + 0.5f);
    }
}