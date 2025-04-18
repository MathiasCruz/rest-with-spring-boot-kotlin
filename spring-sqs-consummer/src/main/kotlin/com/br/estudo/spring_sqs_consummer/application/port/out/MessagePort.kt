package com.br.estudo.spring_sqs_consummer.application.port.out

import com.br.estudo.spring_sqs_consummer.domain.Message

fun interface MessagePort {
    fun receiveMessageFromQueue():Message
}