package com.dol.thirdparty.kakao.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kakao.api")
class KakaoProperties(
    val key: String,
    val url: String
)