package com.amor.sweatchallenge.util

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.provider.SearchRecentSuggestions
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import com.amor.sweatchallenge.presentation.SuggestionProvider
import com.amor.sweatchallenge.presentation.home.HomeAdapter
import com.amor.sweatchallenge.util.pagination.PaginationUtil

class SearchViewUtil internal constructor(private val paginationUtil: PaginationUtil){

    fun setupSearchView(menuItem: MenuItem, activity: Activity?, adapter: HomeAdapter) {
        val searchView = menuItem.actionView as SearchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as? SearchManager
        searchView.setSearchableInfo(searchManager?.getSearchableInfo(activity.componentName))
        addSuggestionListener(searchView)
        addQueryTextListener(activity, searchView, adapter)
    }

    private fun addSuggestionListener(searchView: SearchView) {
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {

            override fun onSuggestionSelect(position: Int): Boolean {
                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val selectedView: CursorAdapter = searchView.suggestionsAdapter
                val cursor: Cursor = selectedView.getItem(position) as Cursor
                val index: Int = cursor.getColumnIndexOrThrow(SearchManager.SUGGEST_COLUMN_TEXT_1)
                searchView.setQuery(cursor.getString(index), true)
                return true
            }

        })
    }

    private fun addQueryTextListener(activity: Activity?, searchView: SearchView, adapter: HomeAdapter) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                paginationUtil.setLoading(true)
                val suggestions = SearchRecentSuggestions(
                    activity,
                    SuggestionProvider.AUTHORITY,
                    SuggestionProvider.MODE
                )
                suggestions.saveRecentQuery(newText, null)
                return true
            }

        })
    }
}