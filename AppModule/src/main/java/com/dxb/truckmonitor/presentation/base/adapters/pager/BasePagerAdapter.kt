package com.dxb.truckmonitor.presentation.base.adapters.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.PagerAdapter
import com.dxb.truckmonitor.presentation.base.adapters.BaseItemListener
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem

abstract class BasePagerAdapter<BINDING : ViewDataBinding, T : BaseListItem, itemListener: Any>(var data:
                                                                     List<T>, val listener: Any): PagerAdapter() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item: T, itemPos: Int)

    fun onPageSelected(index: Int) {
        if(listener is BaseItemListener) {
            listener.onPageSelected(index, this.data[index])
            listener.onListScrolledToEnd(index)
        }
    }

    fun onListScrolledToEnd(index: Int) {
        if(index == this.data.size - 1 && listener is BaseItemListener) {
            listener.onListScrolledToEnd(index)
        }
    }

    fun updateData(list: List<T>) {
        this.data = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int = data.size

    override fun instantiateItem(collection: ViewGroup, position: Int) : Any {

        val binder = DataBindingUtil.inflate<BINDING>(LayoutInflater.from(collection.context), layoutId, null, false)

        bind(binder, data[position], position)

        collection.addView(binder.root)

        return binder.root
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === (`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}