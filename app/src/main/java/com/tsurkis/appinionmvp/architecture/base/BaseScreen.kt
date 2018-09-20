package com.tsurkis.appinionmvp.architecture.base

import android.arch.lifecycle.LifecycleOwner

interface BaseScreen {
    fun getLifeCycleOwnerInstance(): LifecycleOwner
}