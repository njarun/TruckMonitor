package com.dxb.truckmonitor.presentation.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.helpers.TrucksObserver
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.domain.usecase.TrucksUseCase
import com.dxb.truckmonitor.presentation.base.OnException
import com.dxp.micircle.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val trucksUseCase: TrucksUseCase,
                     private val sessionContext: SessionContext, private val trucksObserver: TrucksObserver) : BaseViewModel() {

    private val truckList = MutableLiveData<ArrayList<TruckModel>>()

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
                        truckList.value = it as ArrayList<TruckModel>
                        publishTrucks()
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
        publishTrucks(true)
    }

    private fun publishTrucks(sort: Boolean = false) {
        this.truckList.value?.let {
            trucksObserver.publish(TrucksObserver.TruckData((if(sort) it.reversed() else it) as ArrayList<TruckModel>))
        }
    }
}