package com.app.homeworktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.homeworktest.model.domain.ApiService
import com.app.homeworktest.model.domain.MainRepository
import com.app.homeworktest.model.SignUpRequest
import com.app.homeworktest.model.SignUpResponse
import kotlinx.coroutines.*

class SignUpViewModel : ViewModel() {


    private val apiService: ApiService = ApiService.getInstance()
    private val mainRepository: MainRepository = MainRepository(apiService)

    private val _isSignUpSuccess : MutableLiveData<SignUpResponse> = MutableLiveData()
    val isSignUpSuccess : LiveData<SignUpResponse>
        get() = _isSignUpSuccess

    private val _isError : MutableLiveData<String> = MutableLiveData()
    val isError : LiveData<String>
        get() = _isError

    /**
     * live data for handle Loading and notify for UI
     */
    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    /**
     * call signup api and handle isSuccessful and error (can move to UseCase from viewmodel if want to extend to clean architecture )
     */
    fun doSignUp(userName : String,password : String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.doSignUp(SignUpRequest(userName,password))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _isSignUpSuccess.postValue(response.body())
                    _isLoading.postValue(false)
                } else {
                    _isError.postValue(response.message())
                    _isLoading.postValue(false)
                }
            }
        }

    }

    private fun onError(message: String) {
        _isError.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}