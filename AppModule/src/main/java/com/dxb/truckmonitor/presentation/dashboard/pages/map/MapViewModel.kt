package com.dxb.truckmonitor.presentation.dashboard.pages.map

import android.text.Html
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.dxb.truckmonitor.Config
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.BaseViewModel
import com.dxb.truckmonitor.presentation.base.adapters.BaseItemListener
import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem
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
    val scrollToPos = ObservableInt(0)

    private var googleMap: GoogleMap? = null
    private var currentMarker: Marker? = null
    private var currentIndex = 0

    fun initMap(map: GoogleMap) {

        googleMap = map

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(Config.MAP_DUBAI_LAT, Config.MAP_DUBAI_LON))
            .zoom(Config.MAP_CAMERA_ZOOM).tilt(Config.MAP_CAMERA_TILT)
            .build()

        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        scrollToPos.set(currentIndex)
        updateTruckLocation()
    }

    fun updateTruckList(truckList: ArrayList<TruckModel>) {
        displayTruckList.value = truckList
        scrollToPos.set(currentIndex)
        updateTruckLocation()
    }

    private fun updateTruckLocation() {

        googleMap?.let {

            currentMarker?.remove()

            if(displayTruckList.value == null || displayTruckList.value?.isEmpty() == true) {
                return@let
            }
            else if(currentIndex >= (displayTruckList.value?.size ?: -1)) {
                currentIndex = 0
            }

            val truckModel = displayTruckList.value!![currentIndex]
            val latLng = LatLng(truckModel.lat, truckModel.lng)

            val markerOptions = MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            currentMarker = it.addMarker(markerOptions)

            it.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Config.MAP_CAMERA_ZOOM))
        }
    }

    override fun onPageSelected(index: Int, item: BaseListItem) {
        currentIndex = index
        updateTruckLocation()
    }

    override fun onItemClicked(index: Int, item: BaseListItem) {
        displayTruckList.value?.get(index)?.driverName?.let {
            postMessage(Html.fromHtml("Selected: <big><b>$it</b></big>")) //Temp code no need of string reference
        }
    }
}