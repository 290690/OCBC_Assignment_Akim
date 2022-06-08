package com.app.homeworktest.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.homeworktest.LiveDataTestUtil
import com.app.homeworktest.domain.model.LoginResponse
import com.app.homeworktest.viewmodel.LoginViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user : LoginResponse

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        loginViewModel = LoginViewModel()
        user = mock(LoginResponse::class.java)
    }

    @Test
    fun getUserLogin() { // testing value on liveData
        val userDummy = LoginResponse("Success", "4444", "akhiemz", "test")
        loginViewModel.setDataDummy(userDummy) // ini untuk set data dummy buat testing
        val data =
            LiveDataTestUtil.getValue(loginViewModel.isLoginSuccess) // mulai get data melalui extention fungsi observe di Object LiveDataUtil
        assertNotNull(data) // fungsi untuk memastikan data 'data' tidak kosong
        assertEquals("data null", userDummy, data)
        print("dataDummyLogin: $data")
    }
}