package com.tsurkis.appinionmvp.screens.quotes

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote
import com.tsurkis.appinionmvp.repositories.OpinionatedQuotesRepository
import javax.inject.Inject

class QuotesInteractor @Inject constructor(
        private val quotesRepository: OpinionatedQuotesRepository
) : QuotesScreenContract.Interactor {

    private val timeFrameBetweenRequestsInMilliseconds: Long = 5000
    private var lastRequestTimeFrame: Long = 0L

    private var quotes: MutableLiveData<List<Quote>> = MutableLiveData()

    override fun bindData(lifeCycleOwner: LifecycleOwner, newDataBlock: (List<Quote>?) -> Unit) {
        quotes.observe(
                lifeCycleOwner,
                Observer<List<Quote>> { quotes ->
                    newDataBlock.invoke(quotes)
                }
        )
    }

    override fun requestRandomQuotes(
            requestInTimeFrameBlock: () -> (Unit),
            successBlock: () -> (Unit),
            failureBlock: (timeLeftToMakeNextRequest: Long) -> (Unit)) {
        if (requestIsInTimeFrame()) {
            updateTimeFrame()
            requestInTimeFrameBlock.invoke()
            quotesRepository.requestOpinionatedQuotes { newQuotes ->
                quotes.postValue(newQuotes)
                successBlock.invoke()
            }
        } else {
            failureBlock.invoke(getRemainingTimeUntilNextRequest())
        }
    }

    private fun updateTimeFrame() {
        lastRequestTimeFrame = System.currentTimeMillis()
    }

    private fun requestIsInTimeFrame() =
            System.currentTimeMillis() - lastRequestTimeFrame >= timeFrameBetweenRequestsInMilliseconds

    private fun getRemainingTimeUntilNextRequest() =
            lastRequestTimeFrame - System.currentTimeMillis() + timeFrameBetweenRequestsInMilliseconds
}