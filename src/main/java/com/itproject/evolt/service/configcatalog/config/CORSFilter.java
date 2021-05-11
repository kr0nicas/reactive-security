package com.itproject.evolt.service.configcatalog.config;

import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

public class CORSFilter implements WebFluxConfigurer {

    //TODO: Se debe configurar el COrs para aceptar las petidiones del dominio correspondiente.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }
}
