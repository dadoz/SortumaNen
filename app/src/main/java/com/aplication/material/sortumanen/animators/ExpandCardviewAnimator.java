package com.aplication.material.sortumanen.animators;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

public class ExpandCardviewAnimator {
    private final CardView cardView;
    private WeakReference<Context> ctx;
    private int minHeight;
    private int expandedHeight;

    /**
     *
     * @param card
     * @param context
     */
    public ExpandCardviewAnimator(CardView card, WeakReference<Context> context) {
        cardView = card;
        ctx = context;
        initExpandCardviewAnimator();
    }

    /**
     *
     */
    private void calculateWindowsHeight() {
        WindowManager windowmanager = (WindowManager) ctx.get().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dimension = new DisplayMetrics();
        windowmanager.getDefaultDisplay().getMetrics(dimension);
        expandedHeight = dimension.heightPixels;
    }

    /**
     *
     * @param minHeight
     */
    private void calculateExpandedHeight(int minHeight) {
        expandedHeight = minHeight + 200;
    }

    /**
     *
     */
    private void initExpandCardviewAnimator() {
        cardView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                cardView.getViewTreeObserver().removeOnPreDrawListener(this);
                minHeight = cardView.getHeight();
                ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                layoutParams.height = minHeight;
                cardView.setLayoutParams(layoutParams);
                calculateExpandedHeight(minHeight);
                return true;
            }
        });

    }

    /**
     *
     */
    public boolean toggleCardView() {
        if (cardView.getHeight() == minHeight) {
            expandView();
            return true;
        }

        collapseView();
        return false;
    }

    /**
     *
     */
    private void collapseView() {
        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                minHeight);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
            layoutParams.height = val;
            cardView.setLayoutParams(layoutParams);

        });
        anim.start();
    }

    /**
     *
     */
    private void expandView() {

        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                expandedHeight);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
            layoutParams.height = val;
            cardView.setLayoutParams(layoutParams);
        });
        anim.start();
    }
}
