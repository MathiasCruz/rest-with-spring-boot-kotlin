package com.br.estudo.spring_sqs_consummer.adapter.outbound.database

import com.br.estudo.spring_sqs_consummer.application.port.out.DataBasePort
import com.br.estudo.spring_sqs_consummer.domain.Message
import org.springframework.stereotype.Component

@Component
class PostGresAdapter : DataBasePort{
    override fun saveMessageInDatabase(message: Message) {
       println("Message saved in database: $message")
    }
}