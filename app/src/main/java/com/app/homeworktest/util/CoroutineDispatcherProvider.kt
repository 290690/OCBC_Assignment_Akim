package com.app.homeworktest.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class CoroutineDispatcherProvider {
    open val main:CoroutineDispatcher by lazy { Dispatchers.Main }
    open val io:CoroutineDispatcher by lazy { Dispatchers.IO }
}