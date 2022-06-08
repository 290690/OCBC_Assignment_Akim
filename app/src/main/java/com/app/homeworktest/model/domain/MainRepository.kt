package com.app.homeworktest.model.domain

import com.app.homeworktest.model.*

class MainRepository  constructor(private val apiService: ApiService) {

    suspend fun doLogin(loginRequest : LoginRequest) = apiService.doLogin(loginRequest)
    suspend fun doSignUp(signUpRequest : SignUpRequest) = apiService.doSignUp(signUpRequest)
    suspend fun doTransfer(token : String,transferRequestModel : TransferRequestModel) = apiService.doTransfer(token,transferRequestModel)
    suspend fun getTransactions(token : String) = apiService.getTransactions(token)
    suspend fun getAccountBalance(token : String) = apiService.getAccountBalance(token)
    suspend fun getPayeeList(token : String) = apiService.getPayeeList(token)
 }