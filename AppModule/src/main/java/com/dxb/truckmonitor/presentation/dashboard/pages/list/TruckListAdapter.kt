package com.dxb.truckmonitor.presentation.dashboard.pages.list

import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.databinding.ItemTruckListBinding
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.adapters.recyclerview.BaseAdapter

class TruckListAdapter(list: List<TruckModel>, private val itemListener: ListListener):
                BaseAdapter<ItemTruckListBinding, TruckModel, Any>(list, itemListener) {

    override val layoutId: Int = R.layout.item_truck_list

    override fun bind(binding: ItemTruckListBinding, item: TruckModel, itemPos: Int) {

        binding.apply {

            truckModel = item
            index = itemPos
            listener = itemListener
            executePendingBindings()
        }
    }

    override fun onScrolledToEnd(pos: Int) {
        itemListener.onListScrolledToEnd(pos)
    }
}