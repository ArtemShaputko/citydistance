package com.closer.citydistance.service;

import com.closer.citydistance.cache.CacheMap;
import com.closer.citydistance.model.City;

import com.closer.citydistance.dto.CityDTO;
import com.closer.citydistance.dto.SightDTO;
import com.closer.citydistance.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CityService {
    private final CityRepository cityRepository;
    private final CacheMap<Long, City> cityCache;

    public CityDTO find(Long cityId) {
        City cityFromCache = cityCache.get(cityId);
        if (cityFromCache != null) return CityDTO.toDTO(cityFromCache);
        City cityFromRepository = cityRepository.findById(cityId).orElse(null);
        if (cityFromRepository != null) cityCache.put(cityFromRepository.getId(), cityFromRepository);
        return CityDTO.toDTO(cityFromRepository);
    }


    public List<CityDTO> getAll() {
        return cityRepository.findAll()
                .stream().map(CityDTO::toDTO).toList();
    }

    public City add(City city) {
        return cityRepository.save(city);
    }

    public void remove(Long cityId) {
        cityCache.remove(cityId);
        cityRepository.deleteById(cityId);
    }

    public List<CityDTO> findByNameAndCountry(String name, String country) {
        return cityRepository.findCityByNameAndCountry(name, country)
                .stream().map(CityDTO::toDTO).toList();
    }

    public List<CityDTO> findCityBySightName(String sightName) {
        return cityRepository.findCityBySightName(sightName)
                .stream().map(CityDTO::toDTO).toList();
    }

    public List<SightDTO> getSights(Long cityId) {
        City cityFromRepository = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException("City not found"));
        cityCache.put(cityId, cityFromRepository);
        return cityFromRepository.getSights().stream().map(SightDTO::toDTO).toList();
    }

    public void update(Long cityId, City city) {
        if (!cityRepository.existsById(cityId))
            throw new DataIntegrityViolationException("City " + cityId + " not found");
        city.setId(cityId);
        cityCache.replace(cityId, city);
        cityRepository.save(city);
    }
}