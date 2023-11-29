package com.dol.global.filter

import com.dol.global.security.filter.ExceptionHandlerFilter
import com.dol.global.security.jwt.TokenParseAdapter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val tokenParseAdapter: TokenParseAdapter
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtRequestFilter(tokenParseAdapter), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionHandlerFilter(), JwtRequestFilter::class.java)
    }

}