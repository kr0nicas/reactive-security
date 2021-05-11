package com.itproject.evolt.service.configcatalog.service;


import com.google.cloud.Date;
import com.itproject.evolt.service.configcatalog.dto.CatalogDTO;
import com.itproject.evolt.service.configcatalog.model.Catalog;
import com.itproject.evolt.service.configcatalog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CatalogServiceImpl implements ICatalogService{

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }


    @Override
    public Flux<Catalog> getAllCatalog() {
        return catalogRepository.findAll();
    }

    @Override
    public Flux<Catalog> findByName(String name) {
        return catalogRepository.findByName(name);
    }

    @Override
    public Mono<Catalog> create(CatalogDTO catalog) {
        Catalog catalogEntity = new Catalog(
                catalog.getId(),
              catalog.getIdClient(),
              catalog.getName(),
                catalog.getDescription(),
                catalog.getSujCredit(),
                catalog.getCode(),
                catalog.getStatus(),
                catalog.getIdCatalogParent(),
                Date.fromJavaUtilDate(new java.util.Date()),
                Integer.parseInt("1")
        );


        return catalogRepository.save(catalogEntity);
    }
}
