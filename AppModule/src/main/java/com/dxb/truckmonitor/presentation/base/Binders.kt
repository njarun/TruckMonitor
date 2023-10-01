package com.dxb.truckmonitor.presentation.base

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem
import com.dxb.truckmonitor.presentation.base.adapters.pager.BasePagerAdapter
import com.dxb.truckmonitor.presentation.base.adapters.recyclerview.BaseAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("android:visibility")
fun View.visibility(state: Boolean) {
    visibility = if(state) View.VISIBLE else View.GONE
}

@BindingAdapter("showToast")
fun View.showToast(message: String?) {

    message?.let {

        setOnClickListener {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

@BindingAdapter(value = ["adapter", "dataSet", "scrollToLast", "associatedFab"], requireAll = false) @Suppress("UNCHECKED_CAST")
fun RecyclerView.setRecyclerAdapter(recyclerviewAdapter: BaseAdapter<*, *, *>?, recyclerviewDataset: List<BaseListItem>?, scrollToLast: Boolean, fab: FloatingActionButton?) {

    var listAdapter = recyclerviewAdapter as BaseAdapter<ViewDataBinding, BaseListItem, Any>?
    val layoutManager = layoutManager as LinearLayoutManager
    var scrollToItemPos = layoutManager.findFirstCompletelyVisibleItemPosition()

    listAdapter?.let {

        if(adapter == null) {

            adapter = listAdapter

            clearOnScrollListeners()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    val firstVisiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    it.onPageSelected(firstVisiblePosition)

                    val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                    val itemCount = adapter?.itemCount ?: 0

                    if (lastVisiblePosition == itemCount - 1) {
                        it.onListScrolledToEnd(itemCount - 1)
                    }

                    fab?.let {

                        if (dy > 0) {

                            if (fab.isShown) {
                                fab.hide()
                            }
                        }
                        else if (dy < 0) {

                            if (!fab.isShown) {
                                fab.show()
                            }
                        }
                    }
                }
            })
        }
        else {

            listAdapter = adapter as BaseAdapter<ViewDataBinding, BaseListItem, Any>
        }

        listAdapter!!.updateData(recyclerviewDataset ?: listOf())

        if(scrollToLast && scrollToItemPos >= 0) {
            scrollToItemPos = (listAdapter!!.itemCount - 1)
        }

        if(scrollToItemPos >= 0)
            post{ layoutManager.scrollToPosition(scrollToItemPos) }
    }
}

@BindingAdapter(value = ["adapter", "dataSet", "scrollToPos"], requireAll = false) @Suppress("UNCHECKED_CAST")
fun ViewPager.setPagerAdapter(viewPagerAdapter: BasePagerAdapter<*, *, *>?, pagerDataset: List<BaseListItem>?, scrollToPos: Int) {

    var listAdapter = viewPagerAdapter as BasePagerAdapter<ViewDataBinding, BaseListItem, Any>?
    listAdapter?.let {

        if (adapter == null) {

            adapter = listAdapter

            clearOnPageChangeListeners()
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    it.onListScrolledToEnd(position)
                }

                override fun onPageSelected(position: Int) {
                    it.onPageSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })
        }
        else {

            listAdapter = adapter as BasePagerAdapter<ViewDataBinding, BaseListItem, Any>
        }

        listAdapter!!.updateData(pagerDataset ?: listOf())

        if (scrollToPos >= 0 && scrollToPos < (adapter?.count ?: 0))
            post { setCurrentItem(scrollToPos, false) }
    }
}

@BindingAdapter(value = ["pagerMargin", "pagerPadding"], requireAll = false)
fun ViewPager.setPagerMargin(pagerMargin: Int, pagerPadding: Int) {
    clipToPadding = false
    pageMargin = (pagerMargin * context.resources.displayMetrics.density).toInt()
    val pad = (pagerPadding * context.resources.displayMetrics.density).toInt()
    setPadding(pad,0,pad,0)
}

@BindingAdapter(value = ["imageUrl", "dontScale", "placeholder", "progressView", "errorIcon"], requireAll = false)
fun ImageView.loadImageFromUrlOrPlaceholder(url: String?, dontScale: Boolean?, placeholder: Int?, progressBar: ProgressBar?, errorIcon: ImageView?) {

    if (!url.isNullOrEmpty()) {

        val requestListener: RequestListener<Drawable> = object : RequestListener<Drawable> {

            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {

                errorIcon?.let {

                    if(it.visibility == View.GONE)
                        it.visibility = View.VISIBLE
                }

                progressBar?.let {

                    if(it.visibility == View.VISIBLE)
                        it.visibility = View.GONE
                }

                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {

                errorIcon?.let {

                    if(it.visibility == View.VISIBLE)
                        it.visibility = View.GONE
                }

                progressBar?.let {

                    if(it.visibility == View.VISIBLE)
                        it.visibility = View.GONE
                }

                return false
            }
        }

        if (dontScale != null && dontScale) {

            Glide.with(context).load(url).listener(requestListener).into(this)
        }
        else {

            Glide.with(context).load(url).listener(requestListener).centerCrop().into(this)
        }
    }
    else {

        placeholder?.let {
            Glide.with(context)
                .load(placeholder).into(this)
        }
    }
}