package com.app.homeworktest.model

data class TransferResponseModel(val status : String,
                                 val transactionId : String,
                                 val amount : Double,
                                 val description : String,
                                 val recipientAccount : String)


