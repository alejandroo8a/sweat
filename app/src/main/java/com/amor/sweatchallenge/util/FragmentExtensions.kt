package com.amor.sweatchallenge.util

import android.content.Intent
import android.provider.ContactsContract
import androidx.fragment.app.Fragment

fun Fragment.saveContact(name: String) {
    val intent = Intent(ContactsContract.Intents.Insert.ACTION)
    intent.type = ContactsContract.RawContacts.CONTENT_TYPE
    intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
    startActivity(intent)
}