package com.dol.global.security

import com.dol.domain.user.enums.Authority
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
            // auth
            .mvcMatchers(HttpMethod.POST, "auth/signup").permitAll()
            .mvcMatchers(HttpMethod.POST, "auth/signin").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "auth/reissue").permitAll()
            .mvcMatchers(HttpMethod.DELETE, "auth/logout").permitAll()
            .mvcMatchers(HttpMethod.POST, "auth/send/auth-code").permitAll()
            .mvcMatchers(HttpMethod.GET, "auth/verify/auth-code").permitAll()

            // user
            .mvcMatchers(HttpMethod.GET, "user/my-page").authenticated()
            .mvcMatchers(HttpMethod.GET, "user/profileImage").authenticated()

            // follow
            .mvcMatchers(HttpMethod.POST, "/follow").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/follow/{userIdx}").authenticated()
            .mvcMatchers(HttpMethod.GET, "/follow/recommend").authenticated()

            // hotplace
            .mvcMatchers(HttpMethod.POST, "/hotplace").authenticated()
            .mvcMatchers(HttpMethod.GET, "/hotplace/{idx}").authenticated()
            .mvcMatchers(HttpMethod.POST, "/hoplace/recommend/{idx}").authenticated()
            .mvcMatchers(HttpMethod.GET, "/hotplace/list").authenticated()
            .mvcMatchers(HttpMethod.GET, "/hotplace/my").authenticated()

            // image
            .mvcMatchers(HttpMethod.POST, "/image").authenticated()

            // admin
            .mvcMatchers(HttpMethod.PATCH, "/admin/approve/{hotplace_id}").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.PATCH, "/admin/reject/{hotplace_idx}").hasRole("ADMIN")

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