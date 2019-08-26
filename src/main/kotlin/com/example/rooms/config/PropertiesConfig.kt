package com.example.rooms.config

import org.springframework.beans.factory.config.PropertiesFactoryBean
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
@PropertySource("classpath:application.properties")
class PropertiesConfig{

    @Bean
    fun properties(): PropertiesFactoryBean {
        return PropertiesFactoryBean()
    }

}