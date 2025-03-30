package br.com.estudo.exception.handler

import br.com.estudo.exception.CustomizedResponse
import br.com.estudo.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class CustomizedExceptionResponseHandler :ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<CustomizedResponse> {
        val exception = CustomizedResponse(
            Date(),
            ex.message,
            request.getDescription(false))
        return ResponseEntity<CustomizedResponse>(exception, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFoundException(ex: ResourceNotFoundException, request: WebRequest): ResponseEntity<CustomizedResponse> {
        val exception = CustomizedResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<CustomizedResponse>(exception, HttpStatus.NOT_FOUND)
        }
}