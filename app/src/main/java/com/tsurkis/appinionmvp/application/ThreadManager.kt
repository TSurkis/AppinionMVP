package com.tsurkis.appinionmvp.application

import com.tsurkis.appinionmvp.custom.MainThreadExecutor
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ThreadManager(
        val backThread: Executor = Executors.newSingleThreadExecutor(),
        val mainThread: Executor = MainThreadExecutor()
)

