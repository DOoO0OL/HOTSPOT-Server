package com.dol.global.security

import com.dol.global.filter.FilterConfig
import com.dol.global.security.handler.CustomAccessDeniedHandler
import com.dol.global.security.handler.CustomAuthenticationEntryPoint
import com.dol.global.security.jwt.TokenParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenParser: TokenParser
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .apply(FilterConfig(tokenParser))
        authorizeHttpRequests(http)
        exceptionHandling(http)
        return http.build()
    }

    private fun authorizeHttpRequests(http: HttpSecurity) {
        http.authorizeHttpRequests()
            .mvcMatchers(HttpMethod.POST, "auth/signup").permitAll()
            .mvcMatchers(HttpMethod.POST, "auth/signin").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "auth/reissue").permitAll()
            .anyRequest().permitAll()
    }

    private fun exceptionHandling(http: HttpSecurity) {
        http.exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDeniedHandler())
    }

    @Bean
    protected fun passwordEncode() = BCryptPasswordEncoder()

}