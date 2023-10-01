package com.dxb.truckmonitor.presentation.dashboard

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dxb.truckmonitor.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter(value = ["fragmentManager", "navHostId"], requireAll = true)
fun BottomNavigationView.bindWithNavHost(fragmentManager: FragmentManager, navHostId: Int) {
    val navController = (fragmentManager.findFragmentById(navHostId) as NavHostFragment).navController
    setupWithNavController(navController)
}

@BindingAdapter(value = ["spanText", "normalText"], requireAll = false)
fun TextView.spanText(spanText: String, normalText: String?) {

    val spanEnd = spanText.length
    val textString = spanText + (normalText?.let { " $it" } ?: "")

    val spannable = SpannableString(textString)
    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, R.color.purple_700)),
        0, spanEnd,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        0, spanEnd,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    text = spannable
}