package com.dol.global.filter

import com.dol.global.security.jwt.TokenParseAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtRequestFilter(
    private val tokenParseAdapter: TokenParseAdapter
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val accessToken = tokenParseAdapter.parseAccessToken(request)

        if (!accessToken.isNullOrBlank()) {
            val authentication = tokenParseAdapter.authentication(accessToken)
            SecurityContextHolder.clearContext()
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)

    }

}