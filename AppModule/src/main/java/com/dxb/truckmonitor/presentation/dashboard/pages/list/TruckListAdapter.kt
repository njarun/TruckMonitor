package com.dxb.truckmonitor.presentation.dashboard.pages.list

import com.dxb.truckmonitor.Config
import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.databinding.ItemTruckDriverBinding
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.adapters.BaseItemListener
import com.dxb.truckmonitor.presentation.base.adapters.recyclerview.BaseAdapter

class TruckListAdapter(list: List<TruckModel>, private val itemListener: BaseItemListener):
                BaseAdapter<ItemTruckDriverBinding, TruckModel, Any>(list, itemListener) {

    override val layoutId: Int = R.layout.item_truck_driver

    override fun bind(binding: ItemTruckDriverBinding, item: TruckModel, itemPos: Int) {

        binding.apply {

            truckModel = item
            index = itemPos
            elevation = Config.CARD_ELEVATION_LIST
            listener = itemListener
            executePendingBindings()
        }
    }
}