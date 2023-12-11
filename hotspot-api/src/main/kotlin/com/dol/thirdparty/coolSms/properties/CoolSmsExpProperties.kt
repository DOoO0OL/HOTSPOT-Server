package com.dol.thirdparty.coolSms.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("coolsms.time")
class CoolSmsExpProperties(
    val coolSmsExp: Int
)