package radya.app.core.util

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import radya.app.core.R

fun setupLoading(context: Context, swipeRefreshLayout: SwipeRefreshLayout) {
    swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary))
    swipeRefreshLayout.isEnabled = false
}

fun showLoading(progressLoading: SwipeRefreshLayout) {
    progressLoading.isEnabled = true
    progressLoading.isRefreshing = true
}

fun dismissLoading(progressLoading: SwipeRefreshLayout) {
    progressLoading.isEnabled = true
    progressLoading.isRefreshing = false

    progressLoading.isEnabled = false
}
