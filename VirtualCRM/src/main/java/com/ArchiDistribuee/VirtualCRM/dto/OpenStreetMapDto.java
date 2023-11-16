package com.ArchiDistribuee.VirtualCRM.dto;

import java.util.List;

public record OpenStreetMapDto(
        long place_id,
        String licence,
        String osm_type,
        long osm_id,
        String lat,
        String lon,
        String residential,
        long place_rank,
        double importance,
        String adresstype,
        String name,
        String display_name,
        List<String> boundingbox) {
}
