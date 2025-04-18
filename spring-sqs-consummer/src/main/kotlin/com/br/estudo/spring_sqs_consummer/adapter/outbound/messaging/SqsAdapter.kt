package com.br.estudo.spring_sqs_consummer.adapter.outbound.messaging

import com.br.estudo.spring_sqs_consummer.application.port.out.MessagePort
import com.br.estudo.spring_sqs_consummer.domain.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import java.util.NoSuchElementException
import java.util.UUID

@Component
class SqsAdapter(
    private val sqsClient : SqsAsyncClient,
    @Value("\${aws.sqs.queue.url}")
    private val queueUrl : String
) : MessagePort {
    override fun receiveMessageFromQueue(): Message {
        val receiveMessage = ReceiveMessageRequest.builder()
            .queueUrl(queueUrl)
            .build()
        val responseMessages = sqsClient.receiveMessage(receiveMessage).get()
        return if (responseMessages.messages().isNotEmpty()) {
            val sqsMessage = responseMessages.messages().first()
             Message(
                id = UUID.randomUUID().toString(),
                content = sqsMessage.body(),
                timestamp = System.currentTimeMillis()
                )
        } else {
            throw NoSuchElementException("No messages found in the queue")
        }
    }
}