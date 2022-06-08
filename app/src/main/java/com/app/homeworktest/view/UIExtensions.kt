package com.app.homeworktest.view

import androidx.appcompat.widget.AppCompatButton
import com.app.homeworktest.R

internal fun AppCompatButton.enableButton(){
    isEnabled = true
    setBackgroundColor(resources.getColor(R.color.colorbg_red))
}

internal fun AppCompatButton.disableButton(){
    isEnabled = false
    setBackgroundColor(resources.getColor(R.color.cardview_shadow_start_color))
}