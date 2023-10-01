package com.dxb.truckmonitor.presentation.dashboard.pages.map

import androidx.lifecycle.MutableLiveData
import com.dxb.truckmonitor.Config
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.adapters.BaseItemListener
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem
import com.dxp.micircle.presentation.base.BaseViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(): BaseViewModel(), BaseItemListener {

    val displayTruckList = MutableLiveData<ArrayList<TruckModel>>()
    private lateinit var googleMap: GoogleMap
    private var currentMarker: Marker? = null

    fun updateTruckList(truckList: ArrayList<TruckModel>) {
        displayTruckList.value = truckList
        if(truckList.isNotEmpty())
            updateTruckLocation(truckList[0])
    }

    fun initMap(map: GoogleMap) {

        if(!::googleMap.isInitialized) {

            googleMap = map

            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(Config.MAP_DUBAI_LAT, Config.MAP_DUBAI_LON))
                .zoom(Config.MAP_CAMERA_ZOOM).tilt(Config.MAP_CAMERA_TILT)
                .build()

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    override fun onPageSelected(index: Int, item: BaseListItem) {
        updateTruckLocation(item as TruckModel)
    }

    private fun updateTruckLocation(truckModel: TruckModel) {
        currentMarker?.remove()
        val latLng = LatLng(truckModel.lat, truckModel.lng)
        val markerOptions = MarkerOptions()
            .position(latLng)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        currentMarker = googleMap.addMarker(markerOptions)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Config.MAP_CAMERA_ZOOM))
    }
}