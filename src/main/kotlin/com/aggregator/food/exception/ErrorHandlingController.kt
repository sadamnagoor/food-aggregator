package com.aggregator.food.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Created by Nagoor on 28-July-2020.
 * this is exception handling controller
 * it will handle all the exceptions
 * created for handling exception on
 * general and custom exceptions
 */
@ControllerAdvice
class ErrorHandlingController {
    @ExceptionHandler(Exception::class)
    fun generalException(e: Exception): ResponseEntity<ExceptionResponse> {
        val eR = ExceptionResponse()
        eR.code = HttpStatus.INTERNAL_SERVER_ERROR.value()
        eR.description = e.message
        e.printStackTrace()
        return ResponseEntity(eR, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(CustomException::class)
    fun customException(e: CustomException): ResponseEntity<ExceptionResponse> {
        val eR = ExceptionResponse()
        eR.code = e.code
        eR.description = e.message
        println(e.message)
        return ResponseEntity(eR, HttpStatus.valueOf(e.code))
    }
}