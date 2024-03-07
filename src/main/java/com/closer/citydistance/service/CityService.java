package com.closer.citydistance.service;

import com.closer.citydistance.entity.CityEntity;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    public List<City> getAll(){
        return cityRepository.findAll()
                .stream().map(City::toModel).toList();
    }
    @Transactional
    public CityEntity add(CityEntity city){
        return cityRepository.save(city);
    }
    @Transactional
    public void remove(String name, String country){
        cityRepository.deleteCityEntityByNameAndCountry(name,country);
    }
    public List<City> find(String name, String country){
        return cityRepository.findCityEntityByNameAndCountry(name, country)
                .stream().map(City::toModel).toList();
    }
    public List<Sight> getSights(Long cityId){
        CityEntity city=cityRepository
                .findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException("City not found"));
        return city.getSights().stream().map(Sight::toModel).toList();
    }
    @Transactional
    public void update(Long cityId, CityEntity city){
        if(!cityRepository.existsById(cityId)) throw new DataIntegrityViolationException("City " + cityId + " not found");
        city.setId(cityId);
        cityRepository.save(city);
    }
}