package com.ArchiDistribuee.VirtualCRM.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OpenStreetMapRepository.class)
public class OpenStreetMapRepositoryTest {

    @Autowired
    private OpenStreetMapRepository repository;

    @Test
    void whenGetGeographicPointShouldReturnValid() {

        var city = "angers";
        var street = "2 boulevard de lavoisier";
        var country = "france";
        var postalCode = "49000";

        var testReturn = repository.getGeographicPoint(street, city, country, null, postalCode);

        assertEquals(true, testReturn.isPresent());
        assertEquals("47.4793798", testReturn.get().getLatitude());
        assertEquals("-0.6003838", testReturn.get().getLongitude());
    }

}
