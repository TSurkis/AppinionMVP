package com.tsurkis.appinionmvp.screens.quotes

import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote
import com.tsurkis.appinionmvp.repositories.OpinionatedQuotesRepository
import javax.inject.Inject

class QuotesInteractor @Inject constructor(
        private val quotesRepository: OpinionatedQuotesRepository
) : QuotesScreenContract.Interactor {

    private val timeFrameBetweenRequestsInMilliseconds: Long = 5000
    private var lastRequestTimeFrame: Long = 0L

    override fun requestRandomQuotes(
            requestInTimeFrameBlock: () -> (Unit),
            successBlock: (List<Quote>) -> (Unit),
            failureBlock: (timeLeftToMakeNextRequest: Long) -> (Unit)) {
        if (requestIsInTimeFrame()) {
            updateTimeFrame()
            requestInTimeFrameBlock.invoke()
            quotesRepository.requestOpinionatedQuotes { newQuotes ->
                successBlock.invoke(newQuotes)
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