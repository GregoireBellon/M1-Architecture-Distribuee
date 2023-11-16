package com.ArchiDistribuee.VirtualCRM.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// TODO => attributs temporaires pour pouvoir build, remplacer par ce que renvoie OpenStreetMap
@AllArgsConstructor
@Getter
@Setter
public class GeographicPoint {
    private String latitude, longitude;
}
