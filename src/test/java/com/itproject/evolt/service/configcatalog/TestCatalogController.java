package com.itproject.evolt.service.configcatalog;

import com.google.cloud.firestore.Firestore;
import com.itproject.evolt.service.configcatalog.config.FirestoreConfig;
import com.itproject.evolt.service.configcatalog.controller.CatalogController;
import com.itproject.evolt.service.configcatalog.dto.CatalogDTO;
import com.itproject.evolt.service.configcatalog.repository.CatalogRepository;
import com.itproject.evolt.service.configcatalog.service.CatalogServiceImpl;
import config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CatalogController.class)
@Import({CatalogServiceImpl.class, TestSecurityConfig.class})
public class TestCatalogController {

    private final String apiUrl = "/api/v1/catalog/create";

    @MockBean
    CatalogRepository catalogRepository;

    @MockBean
    Firestore db;

    @InjectMocks
    CatalogServiceImpl catalogService;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    ReactiveJwtDecoder jwtDecoder;

    @Test
    public void testCatalogCreate(){
        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setName("prueba");
        catalogDTO.setDescription("descripcion de la prueba");
        catalogDTO.setCode("ASXZXS");
        catalogDTO.setIdClient("asdasdasd-asdasdasd-asdasd-asdasd");
        catalogDTO.setStatus("ACTIVE");
        catalogDTO.setIdCatalogParent("");
        catalogDTO.setSujCredit("NO");
        catalogDTO.setId(UUID.randomUUID().toString());


        //Mockito.when(catalogRepository.save())
        webTestClient.mutateWith(mockJwt().authorities(new SimpleGrantedAuthority("SCOPE_read:catalog")))
                .post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(catalogDTO))
                .exchange()
                .expectStatus().isCreated();

    }

    private Jwt.Builder jwt() {
        return Jwt.withTokenValue("token").header("alg", "none");
    }


}
