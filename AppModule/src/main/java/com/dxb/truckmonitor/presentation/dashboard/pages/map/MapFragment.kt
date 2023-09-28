package com.dxb.truckmonitor.presentation.dashboard.pages.map

import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.databinding.FragmentMapBinding
import com.dxb.truckmonitor.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val viewModel: MapViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = FragmentMapBinding.inflate(layoutInflater)

    override fun onCreated(viewBinding: ViewBinding) {

        getViewBinding().apply {

            lifecycleOwner = activity
            viewModel = this@MapFragment.viewModel
        }
    }
}