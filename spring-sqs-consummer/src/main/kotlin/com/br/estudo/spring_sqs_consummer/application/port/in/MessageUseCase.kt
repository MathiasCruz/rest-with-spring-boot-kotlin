package com.br.estudo.spring_sqs_consummer.application.port.`in`

import com.br.estudo.spring_sqs_consummer.domain.Message

interface MessageUseCase {
    fun processMessage(message :String) : Unit
}