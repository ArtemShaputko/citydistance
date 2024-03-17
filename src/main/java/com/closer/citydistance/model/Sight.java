package com.closer.citydistance.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sights")
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
