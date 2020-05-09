package com.amor.sweatchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.presentation.home.HomeFragment
import com.amor.sweatchallenge.presentation.home.detail.DetailFragment
import com.amor.sweatchallenge.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentInActivity(HomeFragment(), R.id.rootView, HOME_FRAGMENT_TAG)
    }

    fun addDetailFragment(profileData: ProfileData) {
        addFragmentInActivity(DetailFragment.newInstance(profileData), R.id.rootView, DETAIL_FRAGMENT_TAG)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1) {
            supportFragmentManager.popBackStack()
            super.onBackPressed()
        } else {
            manageBackPressed()
        }
    }
}
