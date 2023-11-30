package com.dol.domain.auth.presentation.data.request

data class SignUpRequest(
    val id: String,
    val name: String,
    val nickName: String,
    val profileUrl: String,
    val password: String,
    val phoneNumber: String,
    val city: String,
    val gu: String,
)