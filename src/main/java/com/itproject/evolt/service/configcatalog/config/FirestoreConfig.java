package com.itproject.evolt.service.configcatalog.config;

import com.google.cloud.firestore.Firestore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class FirestoreConfig {

    @Autowired
    private Firestore firestore;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            log.info("{} app initialized.", firestore.getOptions().getProjectId());
        };
    }
}
