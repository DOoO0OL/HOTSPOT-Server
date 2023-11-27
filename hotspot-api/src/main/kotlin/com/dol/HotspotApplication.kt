package com.dol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.dol"])
class HotspotApplication

fun main(args: Array<String>) {
    runApplication<HotspotApplication>(*args)
}