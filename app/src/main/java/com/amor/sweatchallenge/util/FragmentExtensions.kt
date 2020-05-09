package com.amor.sweatchallenge.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.util.*

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

fun Fragment.showSnackBar(@StringRes message: Int, length: Int = Snackbar.LENGTH_SHORT) {
    view?.apply {
        Snackbar.make(this, message, length).show()
    }
}

fun Fragment.launchCallPhone(phoneNumber: String) {
    val uri = Uri.fromParts("tel", phoneNumber, null)
   startActivity(Intent(Intent.ACTION_DIAL, uri))
}

fun Fragment.launchMap(latitude: String, longitude: String) {
    val uri: String = String.format(
        Locale.ENGLISH,
        "http://maps.google.com/maps?q=loc:%s,%s",
        latitude,
        longitude
    )
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
}