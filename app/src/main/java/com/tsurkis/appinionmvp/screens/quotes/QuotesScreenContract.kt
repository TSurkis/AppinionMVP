package com.tsurkis.appinionmvp.screens.quotes

import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote

interface QuotesScreenContract {

    interface Screen {
        fun showProgressBar()
        fun hideProgressBar()

        fun showList()
        fun hideList()

        fun loadList(quotes: List<Quote>?)

        fun showTimeFrameError(timeLeftToMakeNextRequest: Long)
    }

    interface Presenter {
        fun onViewInitialized()

        fun requestNewData()
    }

    interface Interactor {

        fun requestRandomQuotes(
                requestInTimeFrameBlock: () -> (Unit),
                successBlock: (List<Quote>) -> (Unit),
                failureBlock: (timeLeftToMakeNextRequest: Long) -> (Unit))
    }
}