package com.dol.global.config

import com.dol.global.security.jwt.common.properties.JwtExpTimeProperties
import com.dol.global.security.jwt.common.properties.JwtProperties
import com.dol.thirdparty.coolSms.properties.CoolSmsProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class,
        CoolSmsProperties::class
    ]
)
class PropertiesScanConfig