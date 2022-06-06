package com.app.homework.domain.model

data class TransactionResponse(val status : String,
                               val data : List<TransactionList>)

data class TransactionList(val transactionDate : String,
                           val amount : Double,
                           val receipient : TransactionRecipient)

data class TransactionRecipient(val accountNo : String = "",
                                val accountHolder : String = "")


