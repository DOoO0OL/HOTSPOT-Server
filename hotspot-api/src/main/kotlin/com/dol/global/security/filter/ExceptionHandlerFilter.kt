package com.dol.global.security.filter

import com.dol.global.error.ErrorStatus
import com.dol.global.error.exception.HotSpotException
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
                is ExpiredJwtException -> exceptionToResponse(ErrorStatus.EXPIRED_ACCESS_TOKEN, response)
                is JwtException -> exceptionToResponse(ErrorStatus.INVALID_TOKEN, response)
                is HotSpotException -> exceptionToResponse(exception.errorStatus, response)
                else -> exceptionToResponse(ErrorStatus.SERVER_ERROR, response)
            }
        }
    }

    private fun exceptionToResponse(errorStatus: ErrorStatus, response: HttpServletResponse) {
        val errorResponseToJson = ObjectMapper().writeValueAsString(response)
        response.status = errorStatus.status
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        response.writer.write(errorResponseToJson)
    }

}