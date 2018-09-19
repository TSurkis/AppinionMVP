package com.tsurkis.appinionmvp.dependency.injection

import com.tsurkis.appinionmvp.screens.quotes.QuotesActivity
import com.tsurkis.appinionmvp.screens.quotes.QuotesScreenContract
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBindingModule {

    @ContributesAndroidInjector
    abstract fun quotesActivity(): QuotesActivity

    @Binds
    abstract fun provideQuoteScreen(quotesActivity: QuotesActivity): QuotesScreenContract.Screen
}