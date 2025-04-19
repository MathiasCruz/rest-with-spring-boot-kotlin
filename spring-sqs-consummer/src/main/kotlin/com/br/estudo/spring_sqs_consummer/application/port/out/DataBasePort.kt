package com.br.estudo.spring_sqs_consummer.application.port.out

import com.br.estudo.spring_sqs_consummer.domain.Message

interface DataBasePort {
    fun saveMessageInDatabase(message: Message):Unit
}