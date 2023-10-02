package com.dxb.truckmonitor.presentation.base

import android.text.Spanned
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

open class BaseViewModel : ViewModel() {

    protected val _viewRefreshState = MutableLiveData(false)
    val viewRefreshState: LiveData<Boolean> = _viewRefreshState

    private val subscriptions = CompositeDisposable()

    private val interactionSubject: Subject<Interactor> = PublishSubject.create()
    val interactions: Observable<Interactor> = interactionSubject.hide()

    protected fun emitAction(command: Interactor) {
        interactionSubject.onNext(command)
    }

    protected fun subscription(block: () -> Disposable) {
        subscriptions.add(block())
    }

    fun onRightAction() {
        emitAction(OnRightAction)
    }

    fun postMessage(message: String) {
        emitAction(ShowToast(message))
    }

    fun postMessage(message: Int) {
        emitAction(ShowToast(message))
    }

    fun postMessage(message: Spanned) {
        emitAction(ShowToast(message))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}