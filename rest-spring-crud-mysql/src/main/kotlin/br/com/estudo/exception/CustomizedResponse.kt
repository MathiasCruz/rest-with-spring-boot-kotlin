package br.com.estudo.exception

import java.util.Date

class CustomizedResponse (
    val timestamp: Date,
    val message: String? = null ,
    val details: String
)