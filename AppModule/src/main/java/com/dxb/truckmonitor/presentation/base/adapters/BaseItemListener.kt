package com.dxb.truckmonitor.presentation.base.adapters

interface BaseItemListener {

    fun onItemClicked(index: Int, item: BaseListItem) {

    }

    fun onPageSelected(index: Int, item: BaseListItem) {

    }

    fun onListScrolledToEnd(endIndex: Int) {

    }
}