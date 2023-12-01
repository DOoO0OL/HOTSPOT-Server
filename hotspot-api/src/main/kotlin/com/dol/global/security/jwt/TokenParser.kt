package com.dol.global.security.jwt

import com.dol.domain.user.enums.Authority
import com.dol.global.security.jwt.common.exception.InvalidTokenTypeException
import com.dol.global.security.jwt.common.properties.JwtProperties
import com.dol.global.security.principal.AdminDetailsService
import com.dol.global.security.principal.UserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import javax.servlet.http.HttpServletRequest

@Component
class TokenParser(
    private val userDetailsService: UserDetailsService,
    private val adminDetailsService: AdminDetailsService,
    private val jwtProperties: JwtProperties
) {
     fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.TOKEN_HEADER)
            .let { it ?: return null }
            .let { if (it.startsWith(JwtProperties.TOKEN_PREFIX)) it.replace(JwtProperties.TOKEN_PREFIX, "") else null }

    fun parseRefreshTokenToken(refreshToken: String): String? =
        if (refreshToken.startsWith(JwtProperties.TOKEN_PREFIX)) refreshToken.replace(JwtProperties.TOKEN_PREFIX, "") else null

    fun authentication(token: String): Authentication =
        getAuthority(getTokenBody(token, jwtProperties.accessSecret))
            .let { UsernamePasswordAuthenticationToken(it, "", it.authorities) }

    fun getTokenBody(token: String, secret: Key): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body

    fun getAuthority(body: Claims): UserDetails =
        when (body.get(JwtProperties.AUTHORITY, String::class.java)) {
            Authority.ROLE_USER.name -> userDetailsService.loadUserByUsername(body.subject)
            Authority.ROLE_ADMIN.name -> adminDetailsService.loadUserByUsername(body.subject)
            else -> throw InvalidTokenTypeException()
        }

}
