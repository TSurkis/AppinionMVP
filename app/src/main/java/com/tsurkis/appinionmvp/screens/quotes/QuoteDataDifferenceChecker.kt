package com.tsurkis.appinionmvp.screens.quotes

import android.support.v7.util.DiffUtil
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote

class QuoteDataDifferenceChecker : DiffUtil.ItemCallback<Quote>() {
    override fun areItemsTheSame(oldItem: Quote?, newItem: Quote?) =
            oldItem == newItem

    override fun areContentsTheSame(oldItem: Quote?, newItem: Quote?) =
            oldItem == newItem
}