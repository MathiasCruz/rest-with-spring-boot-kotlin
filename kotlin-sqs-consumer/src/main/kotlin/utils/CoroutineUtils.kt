package org.example.utils

import kotlinx.coroutines.*
import java.lang.Thread.currentThread

fun CoroutineScope.repeatUntilCanceled( dispatcher: CoroutineDispatcher = Dispatchers.Default,
                                        block : suspend () -> Unit) = launch(dispatcher)
{
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