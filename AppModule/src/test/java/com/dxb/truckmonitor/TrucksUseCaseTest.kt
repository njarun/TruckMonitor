package com.dxb.truckmonitor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dxb.truckmonitor.data.router.CoroutineDispatcherProvider
import com.dxb.truckmonitor.data.router.repository.TrucksRepositoryImpl
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.domain.usecase.TrucksUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class TrucksUseCaseTest {

    private lateinit var truckModelList: ArrayList<TruckModel>
    private val emptyTruckModelList = ArrayList<TruckModel>()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var trucksRepository = mock<TrucksRepositoryImpl>()
    private lateinit var trucksUsecase: TrucksUseCase

    @Before
    fun setup() {

        truckModelList = Gson().fromJson(DataStore.truckModelStr, object : TypeToken<ArrayList<TruckModel>>() {}.type)
        trucksUsecase = TrucksUseCase(trucksRepository, SessionContext(), CoroutineDispatcherProvider())
    }

    @Test
    fun `TrucksUse case with data from DB and Empty list from server`() {

        runBlocking {

            whenever(trucksUsecase.getDataFromLocal())
                .thenReturn(truckModelList)

            whenever(trucksUsecase.getDataFromNetwork())
                .thenReturn(emptyTruckModelList)

            mainCoroutineRule.launch {

                trucksUsecase.fetchTruckList().onStart {

                    }
                    .catch {

                    }
                    .collect {

                        if (it is Boolean) {

                        }
                        else if (it is Int) { //No data found string reference

                            trucksUsecase.deleteAllData()
                            Assert.assertEquals(it, R.string.no_data_found)
                        }
                        else if(it is Exception) {

                        }
                        else {

                            Assert.assertEquals(true, (it as ArrayList<TruckModel>).size == 1)
                        }
                    }
            }
        }
    }

    @Test
    fun `TrucksUse case with valid search query`() {

        val searchQuery = "att"

        runBlocking {

            whenever(trucksUsecase.getDataFromLocal(searchQuery))
                .thenReturn(truckModelList)

            mainCoroutineRule.launch {

                trucksUsecase.fetchTruckList(searchQuery).onStart {

                    }
                    .catch {

                    }
                    .collect {

                        Assert.assertEquals(true, it.size == 1)
                    }
            }
        }
    }

    @Test
    fun `TrucksUse case with invalid search query`() {

        val searchQuery = "not_a_valid_search"

        runBlocking {

            whenever(trucksUsecase.getDataFromLocal(searchQuery))
                .thenReturn(truckModelList)

            mainCoroutineRule.launch {

                trucksUsecase.fetchTruckList(searchQuery).onStart {

                }
                    .catch {

                    }
                    .collect {

                        Assert.assertEquals(true, it.size == 0)
                    }
            }
        }
    }
}