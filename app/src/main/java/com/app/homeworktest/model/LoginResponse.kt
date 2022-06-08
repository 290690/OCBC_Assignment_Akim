package com.app.homeworktest.model

data class LoginResponse(val status : String,
                         val token : String,
                         val username : String,
                         val accountNo : String)