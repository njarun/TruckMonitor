package com.dxb.truckmonitor.presentation.dashboard

import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.databinding.ActivityDashboardBinding
import com.dxb.truckmonitor.presentation.base.BaseActivity
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

        viewModel.truckList.observe(this) {

            println("Total trucks - ${it.count()}")
        }
    }
}