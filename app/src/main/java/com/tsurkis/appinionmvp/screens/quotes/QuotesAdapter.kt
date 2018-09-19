package com.tsurkis.appinionmvp.screens.quotes

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tsurkis.appinionmvp.R
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote

class QuotesAdapter(
        quoteDataDifferenceChecker: QuoteDataDifferenceChecker
) : ListAdapter<Quote, QuoteViewHolder>(quoteDataDifferenceChecker) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val cellRootView: View =
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.cell_quote, parent, false)
        return QuoteViewHolder(cellRootView)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}