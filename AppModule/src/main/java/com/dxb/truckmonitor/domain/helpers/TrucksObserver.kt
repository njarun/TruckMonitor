package com.dxb.truckmonitor.domain.helpers

import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class TrucksObserver @Inject constructor() {

    class TruckData(val truckList: ArrayList<TruckModel>?)

    private val subject = PublishSubject.create<TruckData>()

    fun publish(value: TruckData) {
        subject.onNext(value)
    }

    fun getObservable(): Observable<TruckData> {
        return subject.hide()
    }
}