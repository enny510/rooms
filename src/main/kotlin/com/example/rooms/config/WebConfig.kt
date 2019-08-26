package com.example.rooms.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.context.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.PropertySource
import org.thymeleaf.spring5.SpringTemplateEngine
import org.springframework.web.servlet.resource.PathResourceResolver
import org.springframework.web.servlet.resource.GzipResourceResolver
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

@Configuration
@EnableWebMvc
@ComponentScan("com.example.rooms")
open class WebConfig: WebMvcConfigurerAdapter() {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Bean
    open fun templateResolver(): SpringResourceTemplateResolver {
        val templateResolver = SpringResourceTemplateResolver()
        templateResolver.setApplicationContext(applicationContext)
        templateResolver.prefix = "templates/"
        templateResolver.suffix = ".html"
        return templateResolver
    }

    @Bean
    open fun templateEngine(): SpringTemplateEngine {
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        templateEngine.enableSpringELCompiler = true
        return templateEngine
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        val resolver = ThymeleafViewResolver()
        resolver.templateEngine = templateEngine()
        registry.viewResolver(resolver)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
    }
}