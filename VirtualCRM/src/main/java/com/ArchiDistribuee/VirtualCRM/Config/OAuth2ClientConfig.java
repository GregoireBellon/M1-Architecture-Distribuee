package com.ArchiDistribuee.VirtualCRM.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Configuration
public class OAuth2ClientConfig {

        private static final String SALESFORCE_CLIENT_NAME = "salesforce";

        @Value("${spring.datasource.salesforce.username}")
        private  String username;

        @Value("${spring.datasource.salesforce.password}")
        private String password;


        @Value("${spring.datasource.salesforce.base-path}")
        private String basePath;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(authorize -> authorize
                                .anyRequest().permitAll());
                return http.build();
        }

        @Bean
        public OAuth2AuthorizedClientManager authorizedClientManager(
                        ClientRegistrationRepository clientRegistrationRepository,
                        OAuth2AuthorizedClientService authorizedClientService) {
                OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder
                                .builder()
                                .refreshToken()
                                .password()
                                .build();
                AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                                clientRegistrationRepository, authorizedClientService);
                authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
                authorizedClientManager.setContextAttributesMapper(oAuth2AuthorizeRequest -> {
                        if (SALESFORCE_CLIENT_NAME.equals(oAuth2AuthorizeRequest.getClientRegistrationId())) {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME,
                                                username);
                                map.put(OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME,
                                                password);
                                return map;
                        }
                        return null;
                });

                return authorizedClientManager;
        }

        @Bean
        public WebClient salesforceWebClient(OAuth2AuthorizedClientManager authorizedClientManager) {

                ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                                authorizedClientManager);

                oauth2Client.setDefaultClientRegistrationId("salesforce");
                return WebClient.builder()
                                .baseUrl(basePath)
                                .apply(oauth2Client.oauth2Configuration())
                                .build();

        }

}