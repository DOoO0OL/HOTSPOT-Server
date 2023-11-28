package com.dol.global.config

import com.dol.global.security.jwt.common.properties.JwtExpTimeProperties
import com.dol.global.security.jwt.common.properties.JwtProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class
    ]
)
class PropertiesScanConfig