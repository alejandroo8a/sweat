package com.amor.sweatchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.presentation.home.HomeFragment
import com.amor.sweatchallenge.presentation.home.detail.DetailFragment
import com.amor.sweatchallenge.util.addFragmentInActivity
import com.amor.sweatchallenge.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentInActivity(HomeFragment(), R.id.rootView)
    }

    fun addDetailFragment(profileData: ProfileData) {
        addFragmentInActivity(DetailFragment.newInstance(profileData), R.id.rootView)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
