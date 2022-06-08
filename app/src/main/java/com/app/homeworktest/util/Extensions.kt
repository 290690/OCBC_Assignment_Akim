package com.app.homeworktest.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.homeworktest.R


/**
 * AppCompatActivity extension function for add fragment to View
 * @param fragment
 */
internal fun AppCompatActivity.addFragment(fragment : Fragment){
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment, "addFragment")
    transaction.addToBackStack(fragment.tag)
    transaction.commit()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}