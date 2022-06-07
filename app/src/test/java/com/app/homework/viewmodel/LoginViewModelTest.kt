package com.app.homework.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.homework.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


class LoginViewModelTest {
    private lateinit var loginViewModel
    private lateinit var user: User

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        loginViewModel = LoginViewModel()
        user = mock(User::class.java)
    }

    @Test
    fun getUserLogin() { // testing value on liveData
        val userDummy = User("Success", "", "xxx", "test", "123")
        loginViewModel.setDataDummy(userDummy) // ini untuk set data dummy buat testing
        val data = LiveDataTestUtil.getValue(loginViewModel.getUserLogin()) // mulai get data melalui extention fungsi observe di Object LiveDataUtil
        assertNotNull(data) // fungsi untuk memastikan data 'data' tidak kosong
        assertEquals("data null", userDummy, data)
        print("dataDummyLogin: $data")
    }
}