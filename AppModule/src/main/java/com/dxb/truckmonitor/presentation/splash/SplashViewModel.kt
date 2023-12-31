package com.dxb.truckmonitor.presentation.splash

import com.dxb.truckmonitor.presentation.base.AppInterface
import com.dxb.truckmonitor.presentation.base.BaseViewModel
import com.dxb.truckmonitor.presentation.base.OnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {
    val listener = object : AppInterface {
        override fun onCallback(vararg obj: Any) {
            emitAction(OnSuccess)
        }
    }
}