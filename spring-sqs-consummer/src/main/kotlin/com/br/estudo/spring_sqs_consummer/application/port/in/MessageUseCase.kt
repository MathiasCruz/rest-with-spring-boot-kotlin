package com.br.estudo.spring_sqs_consummer.application.port.`in`

import com.br.estudo.spring_sqs_consummer.domain.Message

fun interface MessageUseCase {
    fun receiveMessage() : Message
}