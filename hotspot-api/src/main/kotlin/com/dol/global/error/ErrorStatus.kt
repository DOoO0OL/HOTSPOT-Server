package com.dol.global.error

enum class ErrorStatus(
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_PHONE_NUMBER(409),
    DUPLICATE_USER_ID(409),
    DUPLICATE_NICK_NAME(409),
    PASSWORD_NOT_MATCH(400),
    USER_NOT_FOUND(404),
    DUPLICATE_NEW_PASSWORD(409),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND(404),
    TOO_MANY_AUTHENTICATION_REQUEST(429),

    // SERVER
    SERVER_ERROR(500),

    // TOKEN
    INVALID_TOKEN( 401),
    INVALID_TOKEN_TYPE(401),
    EXPIRED_ACCESS_TOKEN(401),
    EXPIRED_REFRESH_TOKEN(401),

}