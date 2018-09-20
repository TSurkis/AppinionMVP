package com.tsurkis.appinionmvp.screens.quotes

import com.tsurkis.appinionmvp.application.ThreadManager
import com.tsurkis.appinionmvp.architecture.base.BasePresenter
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
        quotesInteractor.bindData(screen.getLifeCycleOwnerInstance(), screen::loadList)
        requestNewData()
    }

    private fun onRequestInTimeFrame() {
        threadManager.mainThread.execute {
            screen.showProgressBar()
            screen.hideList()
        }
    }

    private fun onQuotesRequestSuccess() {
        threadManager.mainThread.execute {
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