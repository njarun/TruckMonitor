package com.dxb.truckmonitor.presentation.dashboard.pages.list

import androidx.lifecycle.MutableLiveData
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.BaseViewModel
import com.dxb.truckmonitor.presentation.base.adapters.BaseItemListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(): BaseViewModel(), BaseItemListener {

    val displayTruckList = MutableLiveData<ArrayList<TruckModel>>()

    fun updateTruckList(truckList: ArrayList<TruckModel>) {
        displayTruckList.value = truckList
    }
}