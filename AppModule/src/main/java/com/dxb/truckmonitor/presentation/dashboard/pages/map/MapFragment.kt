package com.dxb.truckmonitor.presentation.dashboard.pages.map

import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.databinding.FragmentMapBinding
import com.dxb.truckmonitor.domain.helpers.TrucksObserver
import com.dxb.truckmonitor.presentation.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback {

    override val viewModel: MapViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = FragmentMapBinding.inflate(layoutInflater)

    @Inject
    lateinit var trucksObserver: TrucksObserver

    override fun onCreated(viewBinding: ViewBinding) {

        getViewBinding().apply {

            lifecycleOwner = activity
            viewModel = this@MapFragment.viewModel
            adapter = TruckMapAdapter(listOf(), this@MapFragment.viewModel)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.truck_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun initListeners() {

    }

    override fun onMapReady(googleMap: GoogleMap) {

        viewModel.initMap(googleMap)

        addSubscriptions(trucksObserver.getObservable().subscribe {
            it.truckList?.let { truckList ->
                viewModel.updateTruckList(truckList)
            }
        })
    }
}