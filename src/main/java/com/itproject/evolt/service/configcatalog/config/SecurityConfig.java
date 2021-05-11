package com.itproject.evolt.service.configcatalog.config;

import com.itproject.evolt.service.configcatalog.security.UnauthorizedAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${auth0.audience}")
    private String audience;
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerUri;
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                //.pathMatchers("/").permitAll()
               // .pathMatchers("/api/public").permitAll()
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers("/api/v1/catalog/**").hasAuthority("SCOPE_read:catalog")
                .pathMatchers("/api/v1/config/**").hasAuthority("SCOPE_read:configuration")
                .anyExchange().authenticated()
                .and().cors()
                .and().oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }


    @Bean
    public ReactiveJwtDecoder jwtDecoder() {


        NimbusReactiveJwtDecoder nimbusReactiveJwtDecoder = (NimbusReactiveJwtDecoder) ReactiveJwtDecoders.fromOidcIssuerLocation(issuerUri);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator,  new JwtTimestampValidator());

        nimbusReactiveJwtDecoder.setJwtValidator(withAudience);
        return nimbusReactiveJwtDecoder;

    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("SCOPE_");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("permissions");

        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(jwtGrantedAuthoritiesConverter));

        return jwtAuthenticationConverter;
    }



/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Validate tokens through configured OpenID Provider
       *//* http.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        *//*
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .anyRequest().authenticated()).oauth2ResourceServer(
                oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
        );
    }*/

}
