package com.tsurkis.appinionmvp.repositories

import com.tsurkis.appinionmvp.dependency.injection.annotations.ApplicationScope
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.OpinionatedAPIExecutor
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote
import javax.inject.Inject

@ApplicationScope
class OpinionatedQuotesRepository @Inject constructor(
        private val opinionatedAPIExecutor: OpinionatedAPIExecutor
) {

    fun requestOpinionatedQuotes(resultBlock: (List<Quote>) -> (Unit)) {
        opinionatedAPIExecutor.requestRandomOpinionatedQuotes(
                isRandom = "t",
                numberOfQuotes = 10,
                successBlock = resultBlock)
    }
}