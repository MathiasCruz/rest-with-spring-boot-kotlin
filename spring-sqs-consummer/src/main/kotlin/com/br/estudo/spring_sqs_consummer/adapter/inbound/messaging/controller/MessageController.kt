package com.br.estudo.spring_sqs_consummer.adapter.inbound.messaging.controller

import com.br.estudo.spring_sqs_consummer.application.port.`in`.MessageUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class MessageController(
    private val messageUseCase: MessageUseCase
){
    @GetMapping
    fun receiveMessage() = messageUseCase.receiveMessage()
}