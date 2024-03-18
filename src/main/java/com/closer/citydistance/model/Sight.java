package com.closer.citydistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private City city;
}
