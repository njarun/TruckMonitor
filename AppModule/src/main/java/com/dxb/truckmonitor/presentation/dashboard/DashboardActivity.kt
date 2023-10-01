package com.dxb.truckmonitor.presentation.dashboard

import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.databinding.ActivityDashboardBinding
import com.dxb.truckmonitor.presentation.base.BaseActivity
import com.dxb.truckmonitor.presentation.base.Interactor
import com.dxb.truckmonitor.presentation.base.OnRightAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreated(viewBinding: ViewBinding) {
        getViewBinding().apply {
            lifecycleOwner = this@DashboardActivity
            fragmentManager = supportFragmentManager
            viewModel = this@DashboardActivity.viewModel
        }
    }

    override fun handleVMInteractions(interaction: Interactor): Boolean {

        when(interaction) {

            is OnRightAction -> {
                viewModel.sortTruckList()
                return true
            }
        }

        return super.handleVMInteractions(interaction)
    }
}