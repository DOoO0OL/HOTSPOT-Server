package com.dol.global.security.filter

import com.dol.global.error.ErrorCode
import com.dol.global.error.exception.HotSpotException
import com.dol.global.error.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionHandlerFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { exception ->
            when(exception) {
                is ExpiredJwtException -> exceptionToResponse(ErrorCode.EXPIRED_ACCESS_TOKEN, response)
                is JwtException -> exceptionToResponse(ErrorCode.INVALID_TOKEN, response)
                is HotSpotException -> exceptionToResponse(exception.errorCode, response)
            }
        }
    }

    private fun exceptionToResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.status
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        val errorResponse = ErrorResponse(errorCode.message, errorCode.status)
        val errorResponseToJson = ObjectMapper().writeValueAsString(errorResponse)
        response.writer.write(errorResponseToJson)
    }

}