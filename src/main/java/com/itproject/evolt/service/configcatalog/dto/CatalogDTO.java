package com.itproject.evolt.service.configcatalog.dto;

import lombok.Data;
import org.springframework.cloud.gcp.data.firestore.Document;

@Data
public class CatalogDTO {

    private String id;
    private String idClient;
    private String name;
    private String description;
    private String sujCredit;
    private String code;
    private String status;
    private String idCatalogParent;

}
