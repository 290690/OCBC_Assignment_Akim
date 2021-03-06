package com.app.homeworktest.util

import com.app.homeworktest.model.TransactionList
import com.app.homeworktest.view.model.TransactionRecyclerItem

object FoundTransferUtil {


    /**
     * prepare TransactionRecyclerItems section list UI model for adapter
     * group from transaction date
     */
    fun getTransactionList(data : List<TransactionList>) : ArrayList<TransactionRecyclerItem>{

        val transactionList : ArrayList<TransactionRecyclerItem> = arrayListOf()
        var currentDate = data[0].transactionDate.split("T")[0]
        var newDate  = currentDate

        data[0].receipient?.let {
            transactionList.add(TransactionRecyclerItem.TransactionRecyclerTitle(FormatUtil.getDisplayDateString(currentDate)))
            transactionList.add(TransactionRecyclerItem.TransactionRecyclerRow(
                it.accountHolder,
                it.accountNo, data[0].amount))
        }

        for (i in 2 until data.size){
            newDate = data[i].transactionDate.split("T")[0]
            if (newDate != currentDate) {
                currentDate = newDate
                transactionList.add(
                    TransactionRecyclerItem.TransactionRecyclerTitle(FormatUtil.getDisplayDateString(currentDate)))
            }
            data[i].receipient?.let {
                transactionList.add(TransactionRecyclerItem.TransactionRecyclerRow(
                    it.accountHolder,
                    it.accountNo,data[i].amount))
            }
        }
        return transactionList
    }
}