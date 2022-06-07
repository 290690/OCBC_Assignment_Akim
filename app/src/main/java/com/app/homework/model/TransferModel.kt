package com.app.homework.model

data class TransferRequestModel(val receipientAccountNo : String,
                                val amount : Double,
                                val description : String)

data class TransferResponseModel(val amount : Double,
                                 val status : String,
                                 val transactionId : String,
                                 val recipientAccount : String,
                                 val description : String)

