package com.estudo.aws.sqs.kotlin_spring_sqs_producer.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Service
class MessageService(
    private val sqsClient:SqsClient
){
    @Value("\${aws.sqs.queue.url}")
    private lateinit var queueUrl: String

    fun sendMessage(message: String): String{
        val sendMessageRequest = SendMessageRequest.builder()
            .messageBody(message)
            .queueUrl(queueUrl)
            .build()

        return sqsClient.sendMessage(sendMessageRequest).messageId()
    }
}