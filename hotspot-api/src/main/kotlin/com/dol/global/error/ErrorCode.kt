package com.dol.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_PHONE_NUMBER("중복된 전화번호 입니다.", 409),
    DUPLICATE_USER_ID("중복된 id 입니다.", 409),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),
    USER_NOT_FOUND("계정을 찾을 수 없습니다.", 404),
    DUPLICATE_NEW_PASSWORD("기존 비밀번호와 같은 새 비밀번호 입니다.", 409),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND("인증되지 않은 사용자 입니다.", 404),
    TOO_MANY_AUTHENTICATION_REQUEST("인증 메세지 요청을 5번 초과 한 사용자 입니다.", 429),


    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 401),
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", 401),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", 401),

}