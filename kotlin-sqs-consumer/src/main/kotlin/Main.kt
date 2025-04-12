package org.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.config.AwsConfig
import org.example.consumer.SqsConsumer


fun main() = runBlocking{
    val sqs = AwsConfig().getSqsClient()

    val consumer = SqsConsumer(sqs)
    consumer.start()
    delay(3000L)
    consumer.stop()
}