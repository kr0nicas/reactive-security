package com.itproject.evolt.service.configcatalog.controller;


import com.itproject.evolt.service.configcatalog.dto.CatalogDTO;
import com.itproject.evolt.service.configcatalog.model.Catalog;
import com.itproject.evolt.service.configcatalog.service.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//@ApiIgnore use this if you want hide al endpoints in documentation
@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    @Autowired
    private final CatalogServiceImpl catalogService;

    public CatalogController(CatalogServiceImpl catalogService) {
        this.catalogService = catalogService;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Catalog> create(@RequestBody CatalogDTO catalogDTO){
        return catalogService.create(catalogDTO);
    }


}
