package com.app.homeworktest.viewmodel

import androidx.lifecycle.*
import com.app.homeworktest.domain.ApiService
import com.app.homeworktest.domain.MainRepository
import com.app.homeworktest.domain.Response
import com.app.homeworktest.domain.model.PayeeResponseModel
import com.app.homeworktest.domain.model.TransferRequestModel
import com.app.homeworktest.domain.model.TransferResponseModel
import com.app.homeworktest.view.model.PayeeUiModel
import com.app.homeworktest.usecases.FoundTransferUseCase
import com.app.homeworktest.usecases.PayeeListUseCase
import com.app.homeworktest.util.CoroutineDispatcherProvider


class FundTransferViewModel : ViewModel() {

    private var jwtToken : String = ""
    private val apiService: ApiService = ApiService.getInstance()
    private val mainRepository: MainRepository = MainRepository(apiService)
    private val payeeListUseCase = PayeeListUseCase(mainRepository, CoroutineDispatcherProvider())
    private val foundTransferUseCase = FoundTransferUseCase(mainRepository, CoroutineDispatcherProvider())
    private lateinit var transferRequestModel : TransferRequestModel

    private lateinit var payeeModel : PayeeUiModel

    private val _isError : MutableLiveData<String> = MutableLiveData()
    val isError : LiveData<String>
        get() = _isError

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    val isPayeeListSuccess : LiveData<ArrayList<PayeeUiModel>> get() = _isPayeeListSuccess
    private val _isPayeeListSuccess = MediatorLiveData<ArrayList<PayeeUiModel>>()

    val isFoundTransferSuccess  : LiveData<TransferResponseModel> get() = _isFoundTransferSuccess
    private val _isFoundTransferSuccess = MediatorLiveData<TransferResponseModel>()

    private val payeeListListTrigger = MutableLiveData<Unit>()
    private val payeeListListEvent  : LiveData<Response<Any?>> = Transformations.switchMap(payeeListListTrigger){
        payeeListUseCase.executeCase(jwtToken)
    }

    private val foundTransferTrigger = MutableLiveData<Unit>()
    private val foundTransferEvent  : LiveData<Response<Any?>> = Transformations.switchMap(foundTransferTrigger){
        foundTransferUseCase.executeCase(jwtToken,transferRequestModel)
    }

    init {
        _isPayeeListSuccess.addSource(payeeListListEvent){ it ->
            when(it) {
                is Response.SuccessResponse ->{
                    _isPayeeListSuccess.postValue(getCustomObjects(it.response as PayeeResponseModel))
                    _isLoading.postValue(false)
                }
                is Response.ErrorResponse ->{
                    _isError.postValue(it.error)
                    _isLoading.postValue(false)
                }
            }
        }
        _isFoundTransferSuccess.addSource(foundTransferEvent){ it ->
            when(it) {
                is Response.SuccessResponse ->{
                    _isFoundTransferSuccess.postValue(it.response as TransferResponseModel)
                    _isLoading.postValue(false)
                }
                is Response.ErrorResponse ->{
                    _isError.postValue(it.error)
                    _isLoading.postValue(false)
                }
            }
        }
    }

    fun setTransferRequestModel(amount : String,description: String){
        transferRequestModel = TransferRequestModel(payeeModel.accountNumber,amount.replace(",","").toDouble(),description)
        foundTransferTrigger.postValue(Unit)
    }

    fun getPayeeList(){
        payeeListListTrigger.postValue(Unit)
    }

    fun setJwtToken(token : String){
        jwtToken = token
    }
    private fun getCustomObjects(payeeResponseModel: PayeeResponseModel): ArrayList<PayeeUiModel> {
        val customObjects = ArrayList<PayeeUiModel>()

        payeeResponseModel.data.forEach {
            customObjects.add(PayeeUiModel(it.accountNo,it.name))
        }
        return customObjects
    }

    fun setPayeeUiModel(payeeUiModel : PayeeUiModel){
        payeeModel = payeeUiModel
    }

}