package com.example.rooms.config

import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import javax.servlet.ServletException
import javax.servlet.ServletContext
import org.springframework.web.WebApplicationInitializer


class AppInitializer : WebApplicationInitializer {

    @Throws(ServletException::class)
    override fun onStartup(container: ServletContext) {

        val ctx = AnnotationConfigWebApplicationContext()
        ctx.register(WebConfig::class.java)
        ctx.servletContext = container
        val servlet = container.addServlet("dispatcher", DispatcherServlet(ctx))
        servlet.setLoadOnStartup(1)
        servlet.addMapping("/")
    }

}