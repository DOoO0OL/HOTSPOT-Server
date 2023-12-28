plugins {
    kotlin("plugin.jpa") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.7.5")
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
}