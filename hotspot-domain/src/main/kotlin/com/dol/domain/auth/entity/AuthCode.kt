package com.dol.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit

@RedisHash("sms_auth_code")
data class AuthCode(
    @Id
    val phoneNumber: String,
    val authCode: Int,
    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int
)