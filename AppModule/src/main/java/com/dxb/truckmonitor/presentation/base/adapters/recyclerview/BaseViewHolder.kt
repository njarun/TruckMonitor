package com.dxb.truckmonitor.presentation.base.adapters.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<BINDING : ViewDataBinding>(val binder: BINDING) : RecyclerView.ViewHolder(binder.root)