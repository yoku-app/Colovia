package com.yoku.colovia.exceptions.handler

import com.yoku.colovia.exceptions.ArgumentException
import com.yoku.colovia.exceptions.UnauthorizedException
import com.yoku.colovia.exceptions.UserNotFoundException
import com.yoku.guildmaster.entity.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.io.PrintWriter
import java.io.StringWriter

@ControllerAdvice
class GlobalExceptionHandler {


    fun appendStackTraceToCustomError(ex: Throwable, err: ErrorResponse): Unit{
        val stringWriter = StringWriter()
        ex.printStackTrace(PrintWriter(stringWriter))
        err.stackTrace = stringWriter.toString()
    }

    fun handleException(ex: Throwable, status: HttpStatus, includeStackTrace: Boolean = false): ResponseEntity<ErrorResponse>{
        val errorMessage = ex.message ?: "Unknown error occurred"
        val errorResponse = ErrorResponse(status, errorMessage)

        if(includeStackTrace){
            appendStackTraceToCustomError(ex, errorResponse)
        }
        return ResponseEntity.status(status).body(errorResponse)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleOrganisationNotFoundException(ex: UserNotFoundException): ResponseEntity<ErrorResponse> {
        return handleException(ex, HttpStatus.NOT_FOUND, true)
    }

    @ExceptionHandler(ArgumentException::class)
    fun handleInvalidArgumentException(ex: ArgumentException): ResponseEntity<ErrorResponse> {
        return handleException(ex, HttpStatus.BAD_REQUEST, true)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException): ResponseEntity<ErrorResponse> {
        return handleException(ex, HttpStatus.UNAUTHORIZED, true)
    }

}