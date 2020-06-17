package com.amor.sweatchallenge.presentation

import android.content.SearchRecentSuggestionsProvider

class SuggestionProvider : SearchRecentSuggestionsProvider() {

    companion object {
        const val AUTHORITY = "com.amor.sweatchallenge.presentation.SuggestionProvider"

        const val MODE = DATABASE_MODE_QUERIES
    }

    init {
        setupSuggestions(
            AUTHORITY,
            MODE
        )
    }
}