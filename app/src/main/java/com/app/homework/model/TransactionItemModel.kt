package com.app.homework.view.model

sealed class TransactionRecyclerItem {

    data class TransactionRecyclerTitle(val dateTitle : String) : TransactionRecyclerItem()

    data class TransactionRecyclerRow(val accountNumber : String,
                                      val name : String,
                                      val amount : Double) : TransactionRecyclerItem()
}