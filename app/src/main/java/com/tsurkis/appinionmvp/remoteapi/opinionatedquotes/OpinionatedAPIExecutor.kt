package com.tsurkis.appinionmvp.remoteapi.opinionatedquotes

import android.util.Log
import com.tsurkis.appinionmvp.dependency.injection.annotations.ApplicationScope
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.RandomOpinionatedQuotesResponseObject
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

@ApplicationScope
class OpinionatedAPIExecutor @Inject constructor(
        private val api: OpinionatedAPI
) {

    fun requestRandomOpinionatedQuotes(isRandom: String, numberOfQuotes: Int, successBlock: (List<Quote>) -> (Unit)) {
        api
                .requestRandomOpinionatedQuotes(isRandom, numberOfQuotes)
                .enqueue(object: Callback, retrofit2.Callback<RandomOpinionatedQuotesResponseObject> {

                    override fun onFailure(call: Call<RandomOpinionatedQuotesResponseObject>, t: Throwable) {
                        // Ignored for the purpose of simplicity for this sample app.
                        Log.d("hmm", "hmm")
                    }

                    override fun onResponse(call: Call<RandomOpinionatedQuotesResponseObject>, response: Response<RandomOpinionatedQuotesResponseObject>) {
                        response.body()?.let { data ->
                            successBlock.invoke(data.quotes)
                        }
                    }
                })
    }
}