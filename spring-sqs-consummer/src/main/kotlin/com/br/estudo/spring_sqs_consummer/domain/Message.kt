package com.br.estudo.spring_sqs_consummer.domain

data class Message (
    val id: String,
    val content : String,
    val timestamp: Long
)