package com.br.estudo.spring_sqs_consummer.adapter.inbound.messaging

import com.br.estudo.spring_sqs_consummer.application.port.`in`.MessageUseCase
import com.br.estudo.spring_sqs_consummer.application.port.out.MessagePort
import com.br.estudo.spring_sqs_consummer.domain.Message
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class SqsAdapter(
    private val messageUseCase: MessageUseCase
) : MessagePort {

    @SqsListener("\${aws.sqs.queue.url}")
    override fun receiveMessageFromQueue(message: String): Unit {
        messageUseCase.processMessage(message)
    }

}