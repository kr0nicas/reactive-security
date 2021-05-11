package com.itproject.evolt.service.configcatalog.repository;

import com.itproject.evolt.service.configcatalog.dto.CatalogDTO;
import com.itproject.evolt.service.configcatalog.model.Catalog;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.cloud.gcp.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface  CatalogRepository extends FirestoreReactiveRepository<Catalog> {

    Flux<Catalog> findByName(String name);

}
