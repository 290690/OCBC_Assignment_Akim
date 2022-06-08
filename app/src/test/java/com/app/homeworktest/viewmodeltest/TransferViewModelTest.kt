package com.app.homeworktest.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.homeworktest.LiveDataTestUtil
import com.app.homeworktest.model.TransferResponseModel
import com.app.homeworktest.model.PayeeUiModel
import com.app.homeworktest.viewmodel.FundTransferViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TransferViewModelTest {
    private lateinit var transferViewModel: FundTransferViewModel
    private lateinit var transfer: TransferResponseModel
    private lateinit var payees: PayeeUiModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        transferViewModel = FundTransferViewModel()
        transfer = Mockito.mock(TransferResponseModel::class.java)
        payees = Mockito.mock(PayeeUiModel::class.java)
    }

    @Test
    fun getDataPayees() {
        val dataDummy =
            PayeeUiModel("123", "dummy")
        val dummyPayee = ArrayList<PayeeUiModel>()
        dummyPayee.add(dataDummy)
        transferViewModel.setDummyPayees(dummyPayee)
        val data = LiveDataTestUtil.getValue(transferViewModel.isPayeeListSuccess)
        assertNotNull(data)
        assertEquals("data null", dummyPayee, data)
        print("dataDummyPayee: $data")
    }

    @Test
    fun getTransferData() {
        val dummyTransfer = TransferResponseModel(
            "success", "123",
            1.0,"Dummy bills", "123"
        )
        transferViewModel.setDummyTransfer(dummyTransfer)
        val data = LiveDataTestUtil.getValue(transferViewModel.isFoundTransferSuccess)
        assertNotNull(data)
        assertEquals("data null", dummyTransfer, data)
        print("dataDummyTransfer: $data")
    }
}