package com.si7ateck.dz.utility

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout

class ToolbarBehavior : CoordinatorLayout.Behavior<AppBarLayout>() {

    /** Consume if vertical scroll because we don't care about other scrolls */
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout,
                                     directTargetChild: View, target: View, axes: Int, type: Int): Boolean {

      //  getViews(child)
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    /** Perform actual animation by determining the dY amount */
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View,
                                dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int,
                                type: Int, consumed: IntArray) {

        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed,
            dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
     //   getViews(child)

        if (dyConsumed > 0) {
            // RecyclerView is scrolling down so shrink toolbar and translate
            // drawerIcon using the dyConsumed variable
        } else if (dyUnconsumed < 0) {
            // RecyclerView has reached the top and user is scrolling up more to reveal toolbar
            // So use the dyUnconsumed variable to bring back the toolbar to resting position
        }
    }
}