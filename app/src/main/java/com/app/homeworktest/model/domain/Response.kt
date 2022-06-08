package com.app.homeworktest.model.domain

public sealed class Response<out T > {
    public final data class SuccessResponse<out T >(val response: T): Response<T>()
    public final data class ErrorResponse(val error : String) : Response<Any>()
}