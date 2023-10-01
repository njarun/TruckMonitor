package com.dxb.truckmonitor.presentation.base.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dxb.truckmonitor.presentation.base.adapters.BaseItemListener
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem

abstract class BaseAdapter<BINDING : ViewDataBinding, T : BaseListItem, itemListener: Any>(var data: List<T>,
                                           val listener: Any): RecyclerView.Adapter<BaseViewHolder<BINDING>>() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item: T, itemPos: Int)

    override fun getItemCount(): Int = data.size

    fun onPageSelected(pos: Int) {
        if(listener is BaseItemListener)
            listener.onPageSelected(pos, this.data[pos])
    }

    fun onListScrolledToEnd(pos: Int) {
        if(listener is BaseItemListener)
            listener.onListScrolledToEnd(pos)
    }

    fun updateData(list: List<T>) {
        this.data = list
        notifyDataSetChanged()
    }

    fun getItemForPos(position: Int) : BaseListItem? {
        if(data.size > position)
            return data[position]
        else return null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {

        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        bind(holder.binder, data[position], position)
    }
}