package com.closer.citydistance.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@Entity
@Table(name="cities")
public class CityDB {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String country;
    private double lon;
    private double lat;

    @OneToMany(mappedBy = "city")
    Set<CityRating> ratings;
}
