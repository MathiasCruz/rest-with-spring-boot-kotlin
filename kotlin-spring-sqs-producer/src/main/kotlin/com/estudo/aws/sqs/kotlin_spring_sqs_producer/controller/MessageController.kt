package com.estudo.aws.sqs.kotlin_spring_sqs_producer.controller

import com.estudo.aws.sqs.kotlin_spring_sqs_producer.service.MessageService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/messages")
class MessageController(
    private val messageService: MessageService
) {
    @PostMapping
    fun sendMessage(@RequestBody message: String):Map<String, String>
    {
        val messageId = messageService.sendMessage(message)
        return mapOf("messageId" to messageId,
            "status" to "sent")
    }
}