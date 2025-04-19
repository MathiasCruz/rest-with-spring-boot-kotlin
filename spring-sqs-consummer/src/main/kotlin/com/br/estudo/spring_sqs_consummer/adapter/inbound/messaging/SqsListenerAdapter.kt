package com.br.estudo.spring_sqs_consummer.adapter.inbound.messaging

import com.br.estudo.spring_sqs_consummer.application.port.`in`.MessageUseCase
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class SqsListenerAdapter(
    private val messageUseCase: MessageUseCase
)  {

    @SqsListener("\${aws.sqs.queue.url}")
     fun receiveMessageFromQueue(message: String): Unit {
        messageUseCase.processMessage(message)
    }
}