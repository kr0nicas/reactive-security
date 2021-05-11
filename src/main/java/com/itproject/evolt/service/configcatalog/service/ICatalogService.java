package com.itproject.evolt.service.configcatalog.service;

import com.itproject.evolt.service.configcatalog.dto.CatalogDTO;
import com.itproject.evolt.service.configcatalog.model.Catalog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ICatalogService {
    Flux<Catalog> getAllCatalog();

    Flux<Catalog> findByName(String name);

    Mono<Catalog> create(CatalogDTO Catalog);
}
