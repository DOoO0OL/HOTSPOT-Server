package com.dol.thirdparty.coolSms.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("coolsms")
class CoolSmsProperties(
    val access: String,
    val secret: String,
    val phoneNumber: String
)