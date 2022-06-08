package com.app.homeworktest.model

data class LoginRequest(val username : String,
                        val password : String)

data class LoginResponse(val token: String,
                         val accountNo: String,
                         val username: String,
                         val status: String)