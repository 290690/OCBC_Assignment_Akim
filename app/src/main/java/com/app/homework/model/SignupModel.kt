package com.app.homework.model


data class SignUpRequest(val username : String,
                        val password : String)


data class SignUpResponse(val token: String,
                         val status : String)