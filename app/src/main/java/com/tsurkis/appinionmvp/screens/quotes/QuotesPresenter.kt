package com.tsurkis.appinionmvp.screens.quotes

import com.tsurkis.appinionmvp.application.ThreadManager
import com.tsurkis.appinionmvp.architecture.base.BasePresenter
import javax.inject.Inject

class QuotesPresenter @Inject constructor(
        private val quotesInteractor: QuotesInteractor,
        private val threadManager: ThreadManager
) : BasePresenter<QuotesScreenContract.Screen>(), QuotesScreenContract.Presenter {

    override fun requestNewData() {
        threadManager.backThread.execute {
            quotesInteractor.requestRandomQuotes(
                    ::onRequestInTimeFrame,
                    ::onQuotesRequestSuccess,
                    ::onQuotesRequestFailure)
        }
    }

    override fun onViewInitialized() {
        getScreen()?.let { screen ->
            quotesInteractor.bindData(screen.getLifeCycleOwnerInstance(), screen::loadList)
            if (!quotesInteractor.didProvideInitialData()) {
                requestNewData()
            }
        }
    }

    private fun onRequestInTimeFrame() {
        threadManager.mainThread.execute {
            getScreen()?.apply {
                showProgressBar()
                hideList()
            }
        }
    }

    private fun onQuotesRequestSuccess() {
        threadManager.mainThread.execute {
            getScreen()?.apply {
                hideProgressBar()
                showList()
            }
        }
    }

    private fun onQuotesRequestFailure(timeLeftToMakeNextRequest: Long) {
        threadManager.mainThread.execute {
            getScreen()?.apply {
                hideProgressBar()
                showList()
                showTimeFrameError(timeLeftToMakeNextRequest)
            }
        }
    }
}