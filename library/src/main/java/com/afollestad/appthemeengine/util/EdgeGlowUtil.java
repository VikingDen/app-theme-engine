package com.afollestad.appthemeengine.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.EdgeEffect;
import android.widget.ScrollView;

import java.lang.reflect.Field;

/**
 * @author Aidan Follestad (afollestad)
 */
public class EdgeGlowUtil {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setEdgeGlowColor(@NonNull AbsListView listView, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field edgeGlowTop = AbsListView.class.getDeclaredField("mEdgeGlowTop");
                edgeGlowTop.setAccessible(true);
                Field edgeGlowBottom = AbsListView.class.getDeclaredField("mEdgeGlowBottom");
                edgeGlowBottom.setAccessible(true);

                EdgeEffect ee = (EdgeEffect) edgeGlowTop.get(listView);
                ee.setColor(color);
                ee = (EdgeEffect) edgeGlowBottom.get(listView);
                ee.setColor(color);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setEdgeGlowColor(@NonNull ScrollView scrollView, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field edgeGlowTop = ScrollView.class.getDeclaredField("mEdgeGlowTop");
                edgeGlowTop.setAccessible(true);
                Field edgeGlowBottom = ScrollView.class.getDeclaredField("mEdgeGlowBottom");
                edgeGlowBottom.setAccessible(true);

                EdgeEffect ee = (EdgeEffect) edgeGlowTop.get(scrollView);
                ee.setColor(color);
                ee = (EdgeEffect) edgeGlowBottom.get(scrollView);
                ee.setColor(color);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static RecyclerView.OnScrollListener edgeScrollListener;

    public static void setEdgeGlowColor(@NonNull RecyclerView recyclerView, final @ColorInt int color) {
        if (edgeScrollListener == null) {
            edgeScrollListener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    setEdgeGlowColor(recyclerView, color);
                }
            };
            recyclerView.addOnScrollListener(edgeScrollListener);
            return;
        }

        try {
            Field leftGlow = RecyclerView.class.getDeclaredField("mLeftGlow");
            leftGlow.setAccessible(true);
            Field topGlow = RecyclerView.class.getDeclaredField("mTopGlow");
            topGlow.setAccessible(true);
            Field rightGlow = RecyclerView.class.getDeclaredField("mRightGlow");
            rightGlow.setAccessible(true);
            Field bottomGlow = RecyclerView.class.getDeclaredField("mBottomGlow");
            bottomGlow.setAccessible(true);

            EdgeEffectCompat ee = (EdgeEffectCompat) topGlow.get(recyclerView);
            if (ee != null) setEdgeGlowColor(ee, color);
            ee = (EdgeEffectCompat) bottomGlow.get(recyclerView);
            if (ee != null) setEdgeGlowColor(ee, color);
            ee = (EdgeEffectCompat) rightGlow.get(recyclerView);
            if (ee != null) setEdgeGlowColor(ee, color);
            ee = (EdgeEffectCompat) leftGlow.get(recyclerView);
            if (ee != null) setEdgeGlowColor(ee, color);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static void setEdgeGlowColor(@NonNull EdgeEffectCompat edgeEffect, @ColorInt int color) throws Exception {
        Field field = EdgeEffectCompat.class.getDeclaredField("mEdgeEffect");
        field.setAccessible(true);
        EdgeEffect effect = (EdgeEffect) field.get(edgeEffect);
        if (effect != null)
            effect.setColor(color);
    }
}
