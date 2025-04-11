package org.example.utils

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import java.lang.Thread.currentThread

suspend fun CoroutineScope.repeatUntilCanceled(block : suspend () -> Unit) {
    while (isActive) {
        try {
            block()
            yield()
        }
        catch (ex: CancellationException) {
            println("coroutine on ${currentThread().name} cancelled")
        }
    }
    println("coroutine on ${currentThread().name} finished")
}