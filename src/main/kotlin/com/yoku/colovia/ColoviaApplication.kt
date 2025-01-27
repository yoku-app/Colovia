package com.yoku.colovia;

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class ColoviaApplication
fun main(args: Array<String>) {
		runApplication<ColoviaApplication>(*args)
}


