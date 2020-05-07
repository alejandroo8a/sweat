package com.amor.sweatchallenge.util.pagination

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationUtil {

    private var recyclerPaginate: RecyclerView? = null
    var currentPage = 1
    private var isLoading = false
    private var isLastPage = false
    var isFirstPage = false

    fun setRecycler(recyclerView: RecyclerView?) {
        recyclerPaginate = recyclerView
    }

    fun setLoading(loading: Boolean) {
        isLoading = loading
    }

    fun setLastPage(lastPage: Boolean) {
        isLastPage = lastPage
    }

    //Implement interface CallbackLoadMoreItems on each class to use it
    fun addPaginationListener(
        layoutManager: LinearLayoutManager,
        callback: CallbackLoadMoreItems
    ) {
        recyclerPaginate?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                // number of visible items
                val visibleItemCount = layoutManager.childCount
                // number of items in layout
                val totalItemCount = layoutManager.itemCount
                // the position of first visible item
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
                // flag if number of visible items is at the last
                val isAtLastItem =
                    firstVisibleItemPosition + visibleItemCount >= totalItemCount
                // validate non negative values
                val isValidFirstItem = firstVisibleItemPosition >= 0

                // flag to know whether to load more
                val shouldLoadMore =
                    isValidFirstItem && isAtLastItem && isNotLoadingAndNotLastPage
                if (shouldLoadMore) {
                    isFirstPage = false
                    callback.loadItems()
                }
            }
        })

        // load the first page
        isFirstPage = true
        callback.loadItems()
    }

    companion object {
        const val DEFAULT_PAGE = 1
    }
}