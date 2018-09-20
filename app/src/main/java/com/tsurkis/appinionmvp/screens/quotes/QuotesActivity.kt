package com.tsurkis.appinionmvp.screens.quotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.tsurkis.appinionmvp.R
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.responseobjects.Quote
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_quotes.*
import javax.inject.Inject

class QuotesActivity : AppCompatActivity(), QuotesScreenContract.Screen {

    @Inject
    lateinit var presenter: QuotesScreenContract.Presenter

    private lateinit var quotesAdapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        initializeList()

        presenter.onViewInitialized()

        refreshButton.setOnClickListener {
            presenter.requestNewData()
        }
    }

    private fun initializeList() {
        quotesAdapter = QuotesAdapter(QuoteDataDifferenceChecker())
        quotesRecyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = layoutManager
            this.adapter = quotesAdapter
        }
    }

    override fun loadList(quotes: List<Quote>?) {
        quotesAdapter.submitList(quotes)
    }

    override fun showList() {
        quotesRecyclerView.visibility = VISIBLE
    }

    override fun hideList() {
        quotesRecyclerView.visibility = GONE
    }

    override fun showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = GONE
    }

    override fun showTimeFrameError(timeLeftToMakeNextRequest: Long) {
        val text = getString(R.string.quotes_screen_time_frame_error_text, timeLeftToMakeNextRequest)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun getLifeCycleOwnerInstance() = this
}
