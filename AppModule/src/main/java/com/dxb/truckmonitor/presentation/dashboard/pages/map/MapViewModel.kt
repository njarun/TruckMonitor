package com.dxb.truckmonitor.presentation.dashboard.pages.map

import androidx.lifecycle.MutableLiveData
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem
import com.dxb.truckmonitor.presentation.dashboard.pages.list.ListListener
import com.dxp.micircle.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(): BaseViewModel(), ListListener {

    val displayTruckList = MutableLiveData<ArrayList<TruckModel>>()

    fun updateTruckList(truckList: ArrayList<TruckModel>) {
        displayTruckList.value = truckList
    }

    override fun onListItemSelected(index: Int, item: BaseListItem) {

    }

    override fun onListScrolledToEnd(endIndex: Int) {

    }
}