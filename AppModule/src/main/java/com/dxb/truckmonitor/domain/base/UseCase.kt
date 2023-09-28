package com.dxb.truckmonitor.domain.base

interface UseCase<T, U> {

    fun execute(param: T): U
}