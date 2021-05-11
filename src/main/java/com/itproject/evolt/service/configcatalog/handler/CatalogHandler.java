package com.itproject.evolt.service.configcatalog.handler;

import com.google.cloud.Date;
import com.itproject.evolt.service.configcatalog.dto.CatalogDTO;
import com.itproject.evolt.service.configcatalog.model.Catalog;
import com.itproject.evolt.service.configcatalog.repository.CatalogRepository;
import com.itproject.evolt.service.configcatalog.service.CatalogServiceImpl;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Slf4j
public class CatalogHandler {


    @Autowired
    private CatalogServiceImpl catalogService;

    @Autowired
    private CatalogRepository catalogRepository;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();


    public Mono<Catalog> createCatalog(ServerRequest serverRequest){

        Mono<CatalogDTO> body = serverRequest.body(BodyExtractors.toMono(CatalogDTO.class));
        body.flatMap(c -> {
            return Mono.just(Catalog.builder()
                    .code(c.getCode())
                    .name(c.getName())
                    .description(c.getDescription())
                    .id(UUID.randomUUID().toString())
                    .createdDate(Date.fromJavaUtilDate(new java.util.Date()))
                    .sujCredit(c.getSujCredit())
                    .status(c.getStatus())
                    .idCatalogParent(StringUtil.EMPTY_STRING)
                    .build());
        })
                .doOnNext(c -> catalogRepository.save(c))
                .subscribe(p -> System.out.println(p));

        /*return body.flatMap(c -> {
            Flux<Catalog> just = Flux.just(Catalog.builder()
                    .code(c.getCode())
                    .name(c.getName())
                    .description(c.getDescription())
                    .id(UUID.randomUUID().toString())
                    .createdDate(Date.fromJavaUtilDate(new java.util.Date()))
                    .sujCredit(c.getSujCredit())
                    .status(c.getStatus())
                    .idCatalogParent(StringUtil.EMPTY_STRING)
                    .build());
            return just;

        });*/


    }
}
