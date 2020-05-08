package com.amor.sweatchallenge.util

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.saveContact(name: String) {
    val intent = Intent(ContactsContract.Intents.Insert.ACTION)
    intent.type = ContactsContract.RawContacts.CONTENT_TYPE
    intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
    startActivity(intent)
}

fun Fragment.hideKeyboard() {
    val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
}