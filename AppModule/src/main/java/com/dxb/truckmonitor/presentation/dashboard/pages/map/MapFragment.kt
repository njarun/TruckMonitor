package com.dxb.truckmonitor.presentation.dashboard.pages.map

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.databinding.FragmentMapBinding
import com.dxb.truckmonitor.presentation.base.BaseFragment
import com.dxb.truckmonitor.presentation.dashboard.DashboardViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback {

    override val viewModel: MapViewModel by viewModels()
    private val sharedViewModel: DashboardViewModel by activityViewModels()

    override fun constructViewBinding(): ViewBinding = FragmentMapBinding.inflate(layoutInflater)

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
        sharedViewModel.truckList.observe(viewLifecycleOwner) { truckList ->
            truckList?.let { viewModel.updateTruckList(it) }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        viewModel.initMap(googleMap)
    }
}