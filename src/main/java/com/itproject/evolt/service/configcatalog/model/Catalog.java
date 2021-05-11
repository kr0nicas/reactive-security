package com.itproject.evolt.service.configcatalog.model;

import com.google.cloud.Date;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gcp.data.firestore.Document;

@Data
@Builder
@Slf4j
@ToString
@EqualsAndHashCode
@Document(collectionName = "catalog")
@AllArgsConstructor
public class Catalog {

    @DocumentId
    private String id;
    private String idClient;
    private String name;
    private String description;
    private String sujCredit;
    private String code;
    private String status;
    private String idCatalogParent;
    private Date createdDate;
    private int version;

    public Catalog() {
        //Required for firestore
    }

    public Catalog(String name, String description) {
        this.name = name;
        this.description = description;
    }


}
