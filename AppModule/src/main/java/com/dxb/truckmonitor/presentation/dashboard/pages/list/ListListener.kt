package com.dxb.truckmonitor.presentation.dashboard.pages.list

import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem

interface ListListener {

    fun onListItemSelected(index: Int, item: BaseListItem)

    fun onListScrolledToEnd(endIndex: Int)
}