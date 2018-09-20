package com.tsurkis.appinionmvp.architecture.base

import android.arch.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BasePresenter<ScreenType> : ViewModel(), BasePresenterInterface<ScreenType> {

    private var screen: WeakReference<ScreenType>? = null

    override fun attach(screen: ScreenType) {
        this.screen = WeakReference(screen)
    }

    protected fun getScreen(): ScreenType? = screen?.get()
}