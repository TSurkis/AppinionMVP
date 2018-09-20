package com.tsurkis.appinionmvp.application

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tsurkis.appinionmvp.dependency.injection.annotations.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope
class PresenterFactory @Inject constructor(
        private val presenters: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(presenterClass: Class<T>) =
            presenters[presenterClass]?.get() as T
}