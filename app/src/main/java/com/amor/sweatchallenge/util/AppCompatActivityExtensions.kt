package com.amor.sweatchallenge.util

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

const val HOME_FRAGMENT_TAG = "HOME_FRAGMENT"
const val DETAIL_FRAGMENT_TAG = "DETAIL_FRAGMENT"

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int, fragmentTag: String) {
    replaceFragment(supportFragmentManager, fragment, frameId, fragmentTag)
}

fun AppCompatActivity.addFragmentInActivity(fragment: Fragment, frameId: Int, fragmentTag: String) {
    manageFragmentTransaction(supportFragmentManager, fragment, frameId, fragmentTag)
}

fun AppCompatActivity.manageBackPressed() {
    manageBackFlowFragments(supportFragmentManager)
}

private fun manageFragmentTransaction(supportFragmentManager: FragmentManager, fragment: Fragment, frameId: Int, fragmentTag: String) {
    when(fragmentTag) {
        HOME_FRAGMENT_TAG -> {
            if (supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG) != null) {
                supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG)!!).commit()
            } else {
                supportFragmentManager.beginTransaction().add(frameId, fragment, HOME_FRAGMENT_TAG).addToBackStack(fragmentTag).commit()
            }
        }

        DETAIL_FRAGMENT_TAG -> {
            if (supportFragmentManager.findFragmentByTag(DETAIL_FRAGMENT_TAG) != null) {
                supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag(DETAIL_FRAGMENT_TAG)!!).commit()
            } else {
                supportFragmentManager.beginTransaction().add(frameId, fragment, DETAIL_FRAGMENT_TAG).addToBackStack(fragmentTag).commit()
            }

            if (supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG) != null) {
                supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG)!!).commit()
            }
        }
    }
}

private fun manageBackFlowFragments(supportFragmentManager: FragmentManager) {
    when(supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) {
        DETAIL_FRAGMENT_TAG -> {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG)!!).commit()
        }
    }
    supportFragmentManager.popBackStack()
}

private fun replaceFragment(supportFragmentManager: FragmentManager, fragment: Fragment, frameId: Int, fragmentTag: String ) {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(frameId, fragment, fragmentTag)
    fragmentTransaction.addToBackStack(fragmentTag)
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