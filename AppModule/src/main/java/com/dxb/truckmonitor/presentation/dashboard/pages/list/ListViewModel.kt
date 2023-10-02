package com.dxb.truckmonitor.presentation.dashboard.pages.list

import android.text.Html
import androidx.lifecycle.MutableLiveData
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.BaseViewModel
import com.dxb.truckmonitor.presentation.base.adapters.BaseItemListener
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(): BaseViewModel(), BaseItemListener {

    val displayTruckList = MutableLiveData<ArrayList<TruckModel>>()

    fun updateTruckList(truckList: ArrayList<TruckModel>) {
        displayTruckList.value = truckList
    }

    override fun onItemClicked(index: Int, item: BaseListItem) {
        displayTruckList.value?.get(index)?.driverName?.let {
            postMessage(Html.fromHtml("Selected: <big><b>$it</b></big>"))
        }
    }
}