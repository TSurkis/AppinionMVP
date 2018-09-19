package com.tsurkis.appinionmvp.dependency.injection

import com.tsurkis.appinionmvp.screens.quotes.QuotesPresenter
import com.tsurkis.appinionmvp.screens.quotes.QuotesScreenContract
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindQuotesPresenter(quotesPresenter: QuotesPresenter): QuotesScreenContract.Presenter
}