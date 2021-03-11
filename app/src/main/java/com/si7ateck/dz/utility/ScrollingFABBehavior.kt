package com.si7ateck.dz.utility

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.si7ateck.dz.utility.filter.FiltersLayout


class ScrollingFABBehavior(context: Context, attrs: AttributeSet):
    CoordinatorLayout.Behavior<FiltersLayout>(context, attrs) {


    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                                     child: FiltersLayout, directTargetChild: View, target: View,
                                     axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout,
            child, directTargetChild, target, axes, type)
    }


    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FiltersLayout,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )


        if (dyConsumed > 0 && child.visibility == View.VISIBLE) {


            Log.d("akramTest", "hide")

// Start the animation

            child.animate()
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        child.translationY = 0.0f
                        child.visibility = View.INVISIBLE
                    }
                })
                .start()


        } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {

            Log.d("akramTest", "show")
            // Start the animation

            child.animate()
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        child.translationY = 1.0f
                        child.visibility = View.VISIBLE
                    }
                }).start()

        }


    }


}