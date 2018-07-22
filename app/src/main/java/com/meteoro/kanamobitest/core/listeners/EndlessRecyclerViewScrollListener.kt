package com.meteoro.kanamobitest.core.listeners

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener(private val layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private val visibleThreshold: Int = 5
    // The current ofset index of data you have loaded
    private var currentPage: Int = 1
    // The total number of items in the dataset after the last load
    private var previousTotalItemCount: Int = 0
    // True if we are still waiting for the last set of data to load
    private var loading: Boolean = true
    // Sets the startin page index
    private val startingPageIndex: Int = 1

    fun setState(currentPage: Int, totalItems: Int) {
        this.currentPage = currentPage
        this.previousTotalItemCount = totalItems
        this.loading = false
    }

    fun resetState() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        if (recyclerView != null) {
            val totalItemCount = layoutManager.itemCount
            var lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

            // If the total item count is zero and the previous isn't, assume the
            // list is invalidated and should be reset back to initial state
            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex
                this.previousTotalItemCount = totalItemCount
                if (totalItemCount == 0) {
                    this.loading = true
                }
            }

            // If it's still loading, we check to see if the dataset count has
            // changed, if so we conclude it has finished loading and update the current page
            // number and total item count.
            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false
                previousTotalItemCount = totalItemCount
            }

            // If it isn't currently loading, we check to see if we have breached
            // the visibleThreshold and need to reload more data.
            // If we do need to reload some more data, we execute onRequestNextPage to fetch data.
            // threshold should reflect how many total colums there are to
            if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                currentPage++
                onRequestNextPage(currentPage, totalItemCount, recyclerView)
                loading = true
            }
        }
    }

    abstract fun onRequestNextPage(page: Int, totalItemsCount: Int, view: RecyclerView)
}