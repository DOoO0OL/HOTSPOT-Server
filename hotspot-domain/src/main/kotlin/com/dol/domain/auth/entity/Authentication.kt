package com.dol.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit

@RedisHash("sms_authentication")
data class Authentication(
    @Id
    val phoneNumber: String,
    var attemptCount: Int,
    var isVerified: Boolean,
    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int
) {
    constructor(phoneNumber: String): this(

        phoneNumber = phoneNumber,

        attemptCount = 0,

        isVerified = false,

        expiredAt = 300

    )

    fun increaseAuthenticationCount(): Authentication =
        this.copy(attemptCount = attemptCount.inc())

    fun certified(): Authentication =
        this.copy(isVerified = true)
}