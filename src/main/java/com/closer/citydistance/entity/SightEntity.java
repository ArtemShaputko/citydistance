package com.closer.citydistance.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="sights")
public class SightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
}
