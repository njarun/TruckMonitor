package com.dxb.truckmonitor.presentation.dashboard

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.domain.usecase.TrucksUseCase
import com.dxb.truckmonitor.presentation.base.BaseViewModel
import com.dxb.truckmonitor.presentation.base.OnException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val trucksUseCase: TrucksUseCase,
                     private val sessionContext: SessionContext) : BaseViewModel() {

    private val _truckList = MutableLiveData<ArrayList<TruckModel>>()
    val truckList: LiveData<ArrayList<TruckModel>> = _truckList
    private var backupTruckList = ArrayList<TruckModel>()
    private var searchQuery = ""

    init {
        pullTruckList()
    }

    private fun pullTruckList() {

        viewModelScope.launch {

            try {

                trucksUseCase.fetchTruckList().onStart {

                    _viewRefreshState.postValue(true)
                }
                .catch {

                    _viewRefreshState.postValue(false)
                    emitAction(OnException(it))
                }
                .collect {

                    if (it is Boolean) {
                        _viewRefreshState.postValue(it)
                    }
                    else {
                        _viewRefreshState.postValue(false)
                        _truckList.value = it as ArrayList<TruckModel>
                        backupTruckList = _truckList.value as ArrayList<TruckModel>
                    }
                }
            }
            catch (error: Exception) {

                _viewRefreshState.postValue(false)
                error.printStackTrace()
                emitAction(OnException(error))
            }
        }
    }

    fun sortTruckList() {
        sessionContext.updateFeedSortOrder()
        truckList.value?.let {
            _truckList.value = backupTruckList.reversed() as ArrayList<TruckModel>
            backupTruckList = _truckList.value as ArrayList<TruckModel>
            if(searchQuery.isNotEmpty())
                filterTrucks()
        }
    }

    val onQueryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String?): Boolean {
            searchQuery = newText ?: ""
            filterTrucks()
            return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }
    }

    private fun filterTrucks() {
        _truckList.value = if(searchQuery.trim().isEmpty())
            backupTruckList
        else backupTruckList.filter {

            it.plateNo.contains(searchQuery, true)
                    || it.driverName?.contains(searchQuery, true) == true
                    || it.location?.contains(searchQuery, true) == true
        } as ArrayList<TruckModel>
    }
}