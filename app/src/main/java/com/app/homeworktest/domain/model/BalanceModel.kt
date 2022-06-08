package com.app.homeworktest.domain.model

import com.app.homeworktest.domain.Response

data class BalanceResponseModel(val status : String,
                                val accountNo : String,
                                val balance : String)