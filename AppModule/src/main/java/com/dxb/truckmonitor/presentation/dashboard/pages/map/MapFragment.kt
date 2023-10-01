package com.dxb.truckmonitor.presentation.dashboard.pages.map

import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.databinding.FragmentMapBinding
import com.dxb.truckmonitor.domain.helpers.TrucksObserver
import com.dxb.truckmonitor.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val viewModel: MapViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = FragmentMapBinding.inflate(layoutInflater)

    @Inject
    lateinit var trucksObserver: TrucksObserver

    override fun onCreated(viewBinding: ViewBinding) {

        getViewBinding().apply {

            lifecycleOwner = activity
            viewModel = this@MapFragment.viewModel
        }
    }

    override fun initListeners() {

        addSubscriptions(trucksObserver.getObservable().subscribe {
            it.truckList?.let { truckList ->
                viewModel.updateTruckList(truckList)
            }
        })
    }
}