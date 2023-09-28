package com.dxb.truckmonitor.domain.usecase

import com.dxb.truckmonitor.data.dto.model.TruckModel
import com.dxb.truckmonitor.data.router.CoroutineDispatcherProvider
import com.dxb.truckmonitor.domain.router.repository.TrucksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseGetFeeds @Inject constructor(
    private val trucksRepository: TrucksRepository,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) {

    fun fetchTruckList() = flow {

        val trucksLocalList = getDataFromLocal()

        if (trucksLocalList.isNotEmpty()) {
            emit(trucksLocalList)
        }

        emit(true) //to show the fetch progress

        val trucksNetworkList = getDataFromNetwork()

        if (trucksNetworkList.isNotEmpty()) { //Else it will be network or data error, which will be caught in the VM
            deleteAndSaveAllData(trucksNetworkList)
            emit(trucksNetworkList)
        }
    }
        .flowOn(coroutineDispatcherProvider.IO())

    private suspend fun getDataFromLocal(): ArrayList<TruckModel> {
        return trucksRepository.getDataFromLocal()
    }

    private suspend fun getDataFromNetwork(): ArrayList<TruckModel> {
        return trucksRepository.getDataFromNetwork()
    }

    private fun deleteAndSaveAllData(truckList: ArrayList<TruckModel>) {
        CoroutineScope(coroutineDispatcherProvider.IO()).launch {
            trucksRepository.purgeData()
            trucksRepository.saveData(truckList)
        }
    }
}