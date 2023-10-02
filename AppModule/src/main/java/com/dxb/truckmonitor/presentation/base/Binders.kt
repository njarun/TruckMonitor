package com.dxb.truckmonitor.presentation.base

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem
import com.dxb.truckmonitor.presentation.base.adapters.pager.BasePagerAdapter
import com.dxb.truckmonitor.presentation.base.adapters.recyclerview.BaseAdapter

@BindingAdapter(value = ["adapter", "dataSet", "scrollToLast"], requireAll = false) @Suppress("UNCHECKED_CAST")
fun RecyclerView.setRecyclerAdapter(recyclerviewAdapter: BaseAdapter<*, *, *>?, recyclerviewDataset: List<BaseListItem>?, scrollToLast: Boolean) {

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

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun ImageView.loadImageFromUrlOrPlaceholder(url: String?, placeholder: Int?) {
    if(url.isNullOrEmpty())
        placeholder?.let { Glide.with(context)
                .load(it).into(this) }
    else { if(placeholder == null)
            Glide.with(context).load(url).centerCrop().into(this)
        else
            Glide.with(context).load(url).placeholder(ContextCompat.getDrawable(context, placeholder)).centerCrop().into(this)
    }
}