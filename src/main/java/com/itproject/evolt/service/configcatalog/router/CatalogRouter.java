package com.itproject.evolt.service.configcatalog.router;

import com.itproject.evolt.service.configcatalog.handler.CatalogHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CatalogRouter {


    public RouterFunction<ServerResponse> catalogRoute(CatalogHandler catalogHandler){

    }
}
