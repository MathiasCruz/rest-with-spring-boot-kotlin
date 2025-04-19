package com.br.estudo.spring_sqs_consummer.application.service

import com.br.estudo.spring_sqs_consummer.application.port.`in`.MessageUseCase
import com.br.estudo.spring_sqs_consummer.application.port.out.DataBasePort
import com.br.estudo.spring_sqs_consummer.domain.Message
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageServiceImpl(private val databasePort : DataBasePort
    ) : MessageUseCase {

    override fun processMessage(message: String) {
        val processedMessage = Message(
            id = UUID.randomUUID().toString(),
            content = message,
            timestamp = System.currentTimeMillis())
        databasePort.saveMessageInDatabase(processedMessage)
    }

}