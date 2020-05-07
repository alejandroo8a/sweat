package com.amor.sweatchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.presentation.home.HomeFragment
import com.amor.sweatchallenge.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentInActivity(HomeFragment(), R.id.mainView)
    }
}
