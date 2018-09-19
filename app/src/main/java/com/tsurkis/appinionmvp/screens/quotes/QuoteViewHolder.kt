package com.tsurkis.appinionmvp.screens.quotes

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.tsurkis.appinionmvp.R
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote

class QuoteViewHolder(
        rootView: View
) : RecyclerView.ViewHolder(rootView) {

    private val quoteTV: TextView = rootView.findViewById(R.id.quoteTextView)
    private val authorTV: TextView = rootView.findViewById(R.id.authorTextView)

    fun bind(quote: Quote) {
        quoteTV.text = quote.quote
        authorTV.text = quote.author
    }
}