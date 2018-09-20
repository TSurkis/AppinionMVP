package com.tsurkis.appinionmvp.architecture.base

interface BasePresenterInterface<ScreenType> {
    fun attach(screen: ScreenType)
}