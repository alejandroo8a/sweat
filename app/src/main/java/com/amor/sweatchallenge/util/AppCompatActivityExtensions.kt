package com.amor.sweatchallenge.util

import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    replaceFragment(supportFragmentManager, fragment, frameId)
}

fun AppCompatActivity.addFragmentInActivity(fragment: Fragment, frameId: Int) {
    addFragment(supportFragmentManager, fragment, frameId)
}

private fun addFragment(supportFragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.add(frameId, fragment, fragment.tag)
    fragmentTransaction.addToBackStack(fragment::class.java.name)
    fragmentTransaction.commit()
}

private fun replaceFragment(supportFragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(frameId, fragment, fragment.tag)
    fragmentTransaction.addToBackStack(fragment::class.java.name)
    fragmentTransaction.commit()
}

fun AppCompatActivity.launchActivity(cls: Class<*>?, extras: Bundle? = null) {
    val intent = Intent(this, cls).apply {
        extras?.let { bundle ->
            replaceExtras(bundle)
        }
    }
    startActivity(intent)
}

fun AppCompatActivity.launchActivityForResult(cls: Class<*>?, code: Int) {
    val intent = Intent(this, cls)
    startActivityForResult(intent, code)
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}