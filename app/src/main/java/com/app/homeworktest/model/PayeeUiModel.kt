package com.app.homeworktest.model

data class PayeeUiModel(
    var accountNumber: String,
    var name: String
){
    override fun toString(): String {
        return name
    }
}
