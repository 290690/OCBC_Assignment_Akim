package com.app.homeworktest.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.homeworktest.LiveDataTestUtil
import com.app.homeworktest.model.SignUpResponse
import com.app.homeworktest.viewmodel.SignUpViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class RegisterViewModelTest {
    private val TAG = RegisterViewModelTest::class.java.simpleName

    @Mock
    private lateinit var registerViewModel: SignUpViewModel
    private lateinit var user: SignUpResponse

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        registerViewModel = SignUpViewModel()
        user = mock(SignUpResponse::class.java)
    }

    @Test
    fun getUserRegistered() {
        val userDummy = SignUpResponse("Success", "4444",)
        registerViewModel.setDataDummy(userDummy)
        val data = LiveDataTestUtil.getValue(registerViewModel.isSignUpSuccess)
        assertNotNull(data)
        assertEquals("data null", userDummy, data)
        print("data-userRegistered: $data")
    }
}