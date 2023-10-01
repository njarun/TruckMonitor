package com.dxb.truckmonitor.presentation.dashboard.pages.list

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.dxb.truckmonitor.databinding.FragmentListBinding
import com.dxb.truckmonitor.presentation.base.BaseFragment
import com.dxb.truckmonitor.presentation.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    override val viewModel: ListViewModel by viewModels()
    private val sharedViewModel: DashboardViewModel by activityViewModels()

    override fun constructViewBinding(): ViewBinding = FragmentListBinding.inflate(layoutInflater)

    override fun onCreated(viewBinding: ViewBinding) {

        getViewBinding().apply {

            lifecycleOwner = activity
            viewModel = this@ListFragment.viewModel
            adapter = TruckListAdapter(listOf(), this@ListFragment.viewModel)
        }
    }

    override fun initListeners() {
        sharedViewModel.truckList.observe(viewLifecycleOwner) { truckList ->
            truckList?.let { viewModel.updateTruckList(it) }
        }
    }
}