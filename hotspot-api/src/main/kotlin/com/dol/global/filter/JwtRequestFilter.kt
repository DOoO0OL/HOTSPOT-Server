package com.dol.global.filter

import com.dol.global.security.jwt.TokenParser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtRequestFilter(
    private val tokenParser: TokenParser
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val accessToken = tokenParser.parseAccessToken(request)

        if (!accessToken.isNullOrBlank()) {
            val authentication = tokenParser.authentication(accessToken)
            SecurityContextHolder.clearContext()
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)

    }

}