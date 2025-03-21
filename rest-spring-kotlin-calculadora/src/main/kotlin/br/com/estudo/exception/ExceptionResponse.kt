package br.com.estudo.exception

import java.util.Date

class ExceptionResponse (
    val timestamp : Date,
    val message : String?,
    val details : String
)