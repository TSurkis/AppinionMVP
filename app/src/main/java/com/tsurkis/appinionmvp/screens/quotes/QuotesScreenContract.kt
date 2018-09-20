package com.tsurkis.appinionmvp.screens.quotes

import android.arch.lifecycle.LifecycleOwner
import com.tsurkis.appinionmvp.architecture.base.BasePresenterInterface
import com.tsurkis.appinionmvp.architecture.base.BaseScreen
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote

interface QuotesScreenContract {

    interface Screen: BaseScreen {
        fun showProgressBar()
        fun hideProgressBar()

        fun showList()
        fun hideList()

        fun loadList(quotes: List<Quote>?)

        fun showTimeFrameError(timeLeftToMakeNextRequest: Long)
    }

    interface Presenter: BasePresenterInterface<Screen> {
        fun onViewInitialized()

        fun requestNewData()
    }

    interface Interactor {
        fun bindData(lifeCycleOwner: LifecycleOwner, newDataBlock: (List<Quote>?) -> (Unit))

        fun didProvideInitialData(): Boolean

        fun requestRandomQuotes(
                requestInTimeFrameBlock: () -> (Unit),
                successBlock: () -> (Unit),
                failureBlock: (timeLeftToMakeNextRequest: Long) -> (Unit))
    }
}