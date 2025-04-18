package com.br.estudo.spring_sqs_consummer.application.service

import com.br.estudo.spring_sqs_consummer.application.port.`in`.MessageUseCase
import com.br.estudo.spring_sqs_consummer.application.port.out.MessagePort
import com.br.estudo.spring_sqs_consummer.domain.Message
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(private val messagePort: MessagePort) : MessageUseCase {
    override fun receiveMessage() : Message{
        val message =  messagePort.receiveMessageFromQueue()
        println(message)
        return message
    }
}