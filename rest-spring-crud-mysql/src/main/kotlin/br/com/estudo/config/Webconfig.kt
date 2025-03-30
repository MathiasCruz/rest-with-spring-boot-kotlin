package br.com.estudo.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class Webconfig :WebMvcConfigurer {
     override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
         configurer.favorParameter(true)
             .parameterName("mediaType")
             .ignoreAcceptHeader(true)
             .useRegisteredExtensionsOnly(false)
             .defaultContentType(MediaType.APPLICATION_JSON)
             .mediaType("json", MediaType.APPLICATION_JSON)
             .mediaType("xml", MediaType.APPLICATION_XML)
    }
}