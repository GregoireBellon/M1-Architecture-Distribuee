package com.ArchiDistribuee.VirtualCRM.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.ArchiDistribuee.VirtualCRM.dto.OpenStreetMapDto;
import com.ArchiDistribuee.VirtualCRM.entity.GeographicPoint;

import java.util.List;
import java.util.Optional;

@Repository
public class OpenStreetMapRepository {

    private final WebClient client;

    OpenStreetMapRepository(@Value("${spring.datasource.openstreetmap.url}") String openStreetMapUrl) {
        this.client = WebClient.create(openStreetMapUrl);
    }

    public Optional<GeographicPoint> getGeographicPoint(String street, String city, String country, String state,
            String postalCode) {
        List<OpenStreetMapDto> ret = client.get().uri(
                uri -> uri.path("/search")
                        .queryParam("city", city)
                        .queryParam("country", country)
                        .queryParam("postalcode", postalCode)
                        .queryParam("street", street)
                        .queryParam("format", "json")
                        .queryParam("limit", 1).build())
                .retrieve()
                .toEntityList(OpenStreetMapDto.class)
                .block().getBody();

        if (ret.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new GeographicPoint(ret.get(0).lat(), ret.get(0).lon()));
    }
}
