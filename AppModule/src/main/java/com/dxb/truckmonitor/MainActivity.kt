package com.dxb.truckmonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dxb.truckmonitor.data.dto.model.TruckModel
import com.dxb.truckmonitor.domain.usecase.TrucksUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var trucksUseCase: TrucksUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {

            try {

                trucksUseCase.fetchTruckList().onStart {

                }
                    .catch {

                    }
                    .collect {

                        if (it is Boolean) {

                            println("Trucks status - $it")
                        } else {

                            println("Trucks - ${(it as ArrayList<TruckModel>)}")
                        }
                    }
            } catch (error: Exception) {

                error.printStackTrace()
            }
        }
    }
}