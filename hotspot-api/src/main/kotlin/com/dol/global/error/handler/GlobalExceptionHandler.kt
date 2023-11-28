package com.dol.global.error.handler

import com.dol.global.error.exception.HotSpotException
import com.dol.global.error.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(HotSpotException::class)
    fun handler(e: HotSpotException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(e.errorCode.message, e.errorCode.status),
            HttpStatus.valueOf(e.errorCode.status)
        )

}