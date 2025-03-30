package br.com.estudo.config

import br.com.estudo.serialization.converter.Yaml2HttpMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class Webconfig :WebMvcConfigurer {
    private val MEDIA_APPLICATION_YML = MediaType("application", "x-yaml")

     override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        // configurer.favorParameter(true)
           //  .parameterName("mediaType")
             //.ignoreAcceptHeader(true)
             //.useRegisteredExtensionsOnly(false)
             //.defaultContentType(MediaType.APPLICATION_JSON)
             //.mediaType("json", MediaType.APPLICATION_JSON)
             //.mediaType("xml", MediaType.APPLICATION_XML)
         configurer.favorParameter(false)
             .ignoreAcceptHeader(false)
             .ignoreAcceptHeader(false)
             .defaultContentType(MediaType.APPLICATION_JSON)
             .mediaType("json", MediaType.APPLICATION_JSON)
             .mediaType("xml", MediaType.APPLICATION_XML)
             .mediaType("x-yaml", MEDIA_APPLICATION_YML)
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(Yaml2HttpMessageConverter())
    }
}