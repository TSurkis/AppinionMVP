package com.tsurkis.appinionmvp.dependency.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tsurkis.appinionmvp.application.PresenterFactory
import com.tsurkis.appinionmvp.dependency.injection.annotations.PresenterKey
import com.tsurkis.appinionmvp.screens.quotes.QuotesPresenter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindPresenterFactory(factory: PresenterFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @PresenterKey(QuotesPresenter::class)
    abstract fun provideQuotesViewModel(quotesViewModel: QuotesPresenter): ViewModel
}