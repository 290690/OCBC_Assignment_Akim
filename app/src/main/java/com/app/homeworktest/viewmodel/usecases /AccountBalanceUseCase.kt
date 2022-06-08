package com.app.homeworktest.usecases

import androidx.lifecycle.MutableLiveData
import com.app.homeworktest.model.domain.MainRepository
import com.app.homeworktest.model.domain.Response
import com.app.homeworktest.view.listners.CoroutineListener
import com.app.homeworktest.util.CoroutineDispatcherProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AccountBalanceUseCase(private val mainRepository: MainRepository,private val coroutineProvider : CoroutineDispatcherProvider) : CoroutineScope,
    CoroutineListener {

    var job: Job? = null
    override val coroutineContext: CoroutineContext
        get() = coroutineProvider.io

    fun executeCase(jwtToken : String) : MutableLiveData<Response<Any?>>{
        val data = MutableLiveData<Response<Any?>>()
        job  = launch {

            val response = mainRepository.getAccountBalance(jwtToken)
            val dataValue = when {
                response.isSuccessful -> {
                    Response.SuccessResponse(response.body())
                }
                else -> {
                    Response.ErrorResponse(response.message())
                }
            }
            data.postValue(dataValue)
        }
        return data
    }

    override fun cancel() {
        if (coroutineContext.isActive)
            job?.cancel()
    }
}