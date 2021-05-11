package com.itproject.evolt.service.configcatalog.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {



    @GetMapping("/")
    @ResponseBody
    public Mono<String> getRoot(@AuthenticationPrincipal Jwt principal) throws JsonProcessingException {
        Map<String, Object> claims = principal.getClaims();
        ObjectMapper mapperObj = new ObjectMapper();
        String s = mapperObj.writeValueAsString(claims);
        return Mono.just(s);
    }

}
