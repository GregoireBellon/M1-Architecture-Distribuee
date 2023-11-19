package com.ArchiDistribuee.VirtualCRM.Config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;


public class WebClientConfigTest {

    @Test
    public void testSalesforceWebClient() {
        ClientRegistrationRepository clientRegistrationRepository = mock(ClientRegistrationRepository.class);
        OAuth2AuthorizedClientService authorizedClientService = mock(OAuth2AuthorizedClientService.class);

        OAuth2ClientConfig config = new OAuth2ClientConfig();
        OAuth2AuthorizedClientManager manager = config.authorizedClientManager(clientRegistrationRepository, authorizedClientService);

        assertNotNull(manager);
    }
}
