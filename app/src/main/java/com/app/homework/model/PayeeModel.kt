package com.app.homework.model

data class PayeeResponseModel(val status : String,val data : List<Payee>)

data class Payee(val accountNo: String,
                 val id: String,
                 val name: String)

