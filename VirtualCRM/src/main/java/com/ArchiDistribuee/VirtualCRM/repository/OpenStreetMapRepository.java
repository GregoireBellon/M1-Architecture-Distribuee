package com.ArchiDistribuee.VirtualCRM.repository;

import org.springframework.stereotype.Repository;

import com.ArchiDistribuee.VirtualCRM.entity.GeographicPoint;

import java.util.Optional;

@Repository
public class OpenStreetMapRepository {
    // TODO
    public Optional<GeographicPoint> getGeographicPoint(String street, String city, String country, String state,
            String postalCode) {
        return Optional.empty();
    }
}
