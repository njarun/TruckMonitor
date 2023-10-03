package com.dxb.truckmonitor.presentation.dashboard

import androidx.appcompat.widget.SearchView
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.domain.usecase.TrucksUseCase
import com.dxb.truckmonitor.presentation.base.BaseViewModel
import com.dxb.truckmonitor.presentation.base.OnException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val trucksUseCase: TrucksUseCase,
                     private val sessionContext: SessionContext) : BaseViewModel() {

    private val _truckList = MutableLiveData<ArrayList<TruckModel>>()
    val truckList: LiveData<ArrayList<TruckModel>> = _truckList

    val isUserActionsEnabled = ObservableBoolean()
    private var searchQuery = ""
    private var fetchJob: Job? = null
    private var searchJob: Job? = null

    init {
        pullTruckList()
    }

    private fun pullTruckList() {

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {

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
                    else if (it is Int) {
                        _viewRefreshState.postValue(false)
                        _truckList.value = ArrayList()
                        postMessage(it)
                    }
                    else if(it is Exception) {
                        _viewRefreshState.postValue(false)
                        it.printStackTrace()
                        emitAction(OnException(it))
                    }
                    else {

                        isUserActionsEnabled.set(true)
                        _viewRefreshState.postValue(false)

                        if(searchQuery.isNotBlank())
                            searchTruckList()
                        else _truckList.value = it as ArrayList<TruckModel>
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

    private fun searchTruckList() {

        searchJob?.cancel()
        searchJob = viewModelScope.launch {

            try {

                trucksUseCase.fetchTruckList(searchQuery).onStart {
                    _viewRefreshState.postValue(true)
                }
                .catch {
                    _viewRefreshState.postValue(false)
                }
                .collect {

                    _viewRefreshState.postValue(false)
                    _truckList.value = it
                }
            }
            catch (error: Exception) {
                _viewRefreshState.postValue(false)
            }
        }
    }

    fun onRefresh() {
        pullTruckList()
    }

    fun sortTruckList() {
        sessionContext.updateFeedSortOrder()
        truckList.value?.isNotEmpty().let {
            _truckList.value = (truckList.value?.reversed() ?: ArrayList()) as ArrayList<TruckModel>
        }
    }

    val onQueryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(searchText: String?): Boolean {
            searchQuery = searchText ?: ""
            searchTruckList()
            return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }
    }
}