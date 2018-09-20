package com.tsurkis.appinionmvp.dependency.injection.annotations

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@MapKey
annotation class PresenterKey(val keyValue: KClass<out ViewModel>)