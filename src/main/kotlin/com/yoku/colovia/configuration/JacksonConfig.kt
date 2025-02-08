package com.yoku.colovia.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        dateFormat.timeZone = TimeZone.getTimeZone("CET")
        val objectMapper = ObjectMapper()
        // Register the JavaTimeModule to handle LocalDate and ZonedDateTime
        val javaTimeModule = JavaTimeModule()
        objectMapper.registerModule(javaTimeModule) // Register JavaTimeModule with custom deserializer
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.findAndRegisterModules()
        objectMapper.dateFormat = dateFormat
        return objectMapper
    }
}
