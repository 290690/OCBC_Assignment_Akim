package com.app.homeworktest.model

data class PayeeUiModel(
    var name: String,
    var accountNumber: String
){
    override fun toString(): String {
        return name
    }
}
