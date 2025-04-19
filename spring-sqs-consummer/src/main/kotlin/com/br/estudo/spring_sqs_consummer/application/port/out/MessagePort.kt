package com.br.estudo.spring_sqs_consummer.application.port.out

interface MessagePort {
    fun receiveMessageFromQueue(message :String = "") : Unit
}