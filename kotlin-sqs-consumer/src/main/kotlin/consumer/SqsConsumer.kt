package org.example.consumer

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.future.await
import org.example.utils.repeatUntilCanceled
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import kotlin.coroutines.CoroutineContext

class SqsConsumer(
    private val sqs:SqsAsyncClient
) : CoroutineScope {

    private val supervisorJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + supervisorJob

    fun start() = launch {
        val messageChanel = Channel<Message>()
        repeat(2) { launchWorker(messageChanel) }
        launchMsgReceiver(messageChanel)
    }
    fun stop(){
        supervisorJob.cancel()
    }

    private fun CoroutineScope.launchWorker(channel: ReceiveChannel<Message>) = launch {
        repeatUntilCanceled {
            for (msg in channel) {
                processMessage(msg)
            }
        }
    }

    private suspend fun processMessage(msg: Message) {
        println("${Thread.currentThread().name} processing message ${msg.body()}")
        delay((1000..2000L).random())
        println("${Thread.currentThread().name} processed message ${msg.body()}")
    }

    private suspend fun deleteMessage(msg: Message) {
        sqs.deleteMessage{ req ->
            req.queueUrl("https://localhost.localstack.cloud:4566/000000000000/test-queue")
            req.receiptHandle(msg.receiptHandle())
        }.await()
        println("Deleted message ${msg.messageId()}")
    }

    private fun CoroutineScope.launchMsgReceiver(channel: SendChannel<Message>) = launch {
        repeatUntilCanceled {
            val receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl("https://localhost.localstack.cloud:4566/000000000000/test-queue")
                .waitTimeSeconds(20)
                .maxNumberOfMessages(5)
                .build()

            val messages = sqs.receiveMessage(receiveRequest).await().messages()
            messages.forEach { channel.send(it) }
        }
    }
}