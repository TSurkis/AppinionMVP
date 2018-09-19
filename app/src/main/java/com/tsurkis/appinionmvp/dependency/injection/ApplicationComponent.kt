package com.tsurkis.appinionmvp.dependency.injection

import android.content.Context
import com.tsurkis.appinionmvp.application.App
import com.tsurkis.appinionmvp.dependency.injection.annotations.ApplicationContext
import com.tsurkis.appinionmvp.dependency.injection.annotations.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(modules = [
    AndroidInjectionModule::class,
    ScreenBindingModule::class,
    PresenterModule::class,
    ThreadingModule::class,
    RemoteAPIModule::class
])
interface ApplicationComponent : AndroidInjector<App> {

    override fun inject(instance: App)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(@ApplicationContext applicationContext: Context): Builder
    }
}