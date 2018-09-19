package com.tsurkis.appinionmvp.dependency.injection

import com.tsurkis.appinionmvp.application.ThreadManager
import com.tsurkis.appinionmvp.dependency.injection.annotations.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ThreadingModule {

    @Provides
    @ApplicationScope
    fun provideThreadManager() = ThreadManager()
}