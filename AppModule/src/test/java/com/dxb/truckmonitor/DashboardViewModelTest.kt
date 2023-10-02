package com.dxb.truckmonitor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dxb.truckmonitor.data.router.CoroutineDispatcherProvider
import com.dxb.truckmonitor.data.router.repository.TrucksRepositoryImpl
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.domain.usecase.TrucksUseCase
import com.dxb.truckmonitor.presentation.dashboard.DashboardViewModel
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val sessionContext = mock<SessionContext>()
    private var trucksRepository = mock<TrucksRepositoryImpl>()

    private lateinit var trucksUsecase: TrucksUseCase
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {

        trucksUsecase = TrucksUseCase(trucksRepository, CoroutineDispatcherProvider())
    }

    @Test
    fun `loading state of view model`() = runBlocking {

        val trucksList = createTrucksList(110)

        whenever(sessionContext.feedSortOrder)
            .thenReturn(SessionContext.FEED_SORT_ORDER.ASC)

        whenever(trucksRepository.getDataFromLocal())
            .thenReturn(trucksList)

        whenever(trucksRepository.getDataFromNetwork())
            .thenReturn(trucksList)

        viewModel = DashboardViewModel(trucksUsecase, sessionContext)

        TimeUnit.SECONDS.sleep(5)

        Assert.assertEquals(trucksList.size, viewModel.truckList.value?.size)
    }

    private fun createTrucksList(startId: Long): ArrayList<TruckModel> {
        val trucksList: ArrayList<TruckModel> = ArrayList()
        for (id in startId - 10..startId)
            trucksList.add(createTrucksObject(id))
        return trucksList
    }

    private fun createTrucksObject(id: Long): TruckModel {
        val truckData = "{\"plateNo\":\"$id M 21753\",\"driverName\":\"$id Gabriel Julian\",\"lat\":25.11894,\"lng\":55.183552,\"location\":\"Al Sufouh, Dubai, United Arab Emirates\",\"imageURL\":\"https://i.picsum.photos/id/271/200/300.jpg\",\"lastUpdated\":1696214280000}"
        return Gson().fromJson(truckData, TruckModel::class.java)
    }
}