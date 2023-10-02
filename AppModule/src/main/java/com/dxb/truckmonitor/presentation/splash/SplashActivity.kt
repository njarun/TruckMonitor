package com.dxb.truckmonitor.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.databinding.ActivitySplashBinding
import com.dxb.truckmonitor.presentation.base.BaseActivity
import com.dxb.truckmonitor.presentation.base.Interactor
import com.dxb.truckmonitor.presentation.base.OnSuccess
import com.dxb.truckmonitor.presentation.base.OpenNextScreen
import com.dxb.truckmonitor.presentation.dashboard.DashboardActivity
import com.dxb.truckmonitor.utils.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint @SuppressLint("CustomSplashScreen") //Customizations are limited - let it evolve! -- nj
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        Utility.makeActivityFullScreen(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreated(viewBinding: ViewBinding) {
        getViewBinding().apply {
            lifecycleOwner = this@SplashActivity
            viewModel = this@SplashActivity.viewModel
        }
    }

    override fun handleVMInteractions(interaction: Interactor): Boolean {
        when(interaction) { is OnSuccess -> return super.handleVMInteractions(OpenNextScreen(DashboardActivity::class.java)) }
        return super.handleVMInteractions(interaction)
    }
}