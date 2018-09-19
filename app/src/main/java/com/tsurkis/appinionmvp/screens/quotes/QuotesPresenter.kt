package com.tsurkis.appinionmvp.screens.quotes

import com.tsurkis.appinionmvp.application.ThreadManager
import com.tsurkis.appinionmvp.architecture.base.BasePresenter
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote
import javax.inject.Inject

class QuotesPresenter @Inject constructor(
        screen: QuotesScreenContract.Screen,
        private val quotesInteractor: QuotesInteractor,
        private val threadManager: ThreadManager
) : BasePresenter<QuotesScreenContract.Screen>(screen), QuotesScreenContract.Presenter {

    override fun requestNewData() {
        threadManager.backThread.execute {
            quotesInteractor.requestRandomQuotes(
                    ::onRequestInTimeFrame,
                    ::onQuotesRequestSuccess,
                    ::onQuotesRequestFailure)
        }
    }

    override fun onViewInitialized() {
        requestNewData()
    }

    private fun onRequestInTimeFrame() {
        threadManager.mainThread.execute {
            screen.showProgressBar()
            screen.hideList()
        }
    }

    private fun onQuotesRequestSuccess(quotes: List<Quote>) {
        threadManager.mainThread.execute {
            screen.loadList(quotes)
            screen.hideProgressBar()
            screen.showList()
        }
    }

    private fun onQuotesRequestFailure(timeLeftToMakeNextRequest: Long) {
        threadManager.mainThread.execute {
            screen.hideProgressBar()
            screen.showList()
            screen.showTimeFrameError(timeLeftToMakeNextRequest)
        }
    }
}