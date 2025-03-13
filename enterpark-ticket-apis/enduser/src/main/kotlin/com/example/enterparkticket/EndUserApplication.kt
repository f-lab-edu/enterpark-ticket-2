package com.example.enterparkticket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses = [EndUserApplication::class])
@ConfigurationPropertiesScan
class EndUserApplication

fun main(args: Array<String>) {
    runApplication<EndUserApplication>(*args)
}
