package com.dol.domain.auth.presentation.data.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: Long,
    val refreshTokenExp: Long,
)