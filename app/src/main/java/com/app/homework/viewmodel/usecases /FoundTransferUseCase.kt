package com.app.homework.usecases

import androidx.lifecycle.MutableLiveData
import com.app.homework.model.domain.MainRepository
import com.app.homework.model.domain.Response
import com.app.homework.model.TransferRequestModel
import com.app.homework.view.listners.CoroutineListener
import com.app.homework.util.CoroutineDispatcherProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FoundTransferUseCase (private val mainRepository: MainRepository,private val coroutineProvider : CoroutineDispatcherProvider) : CoroutineScope,
    CoroutineListener {

    var job: Job? = null
    override val coroutineContext: CoroutineContext
        get() = coroutineProvider.io

    fun executeCase(token : String,transferRequestModel : TransferRequestModel) : MutableLiveData<Response<Any?>> {
        val data = MutableLiveData<Response<Any?>>()
        job  = launch {

            val response = mainRepository.doTransfer(token,transferRequestModel)
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