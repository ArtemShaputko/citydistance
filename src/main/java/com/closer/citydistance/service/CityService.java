package com.closer.citydistance.service;

import com.closer.citydistance.cache.CacheMap;
import com.closer.citydistance.mapper.Mapper;
import com.closer.citydistance.model.City;

import com.closer.citydistance.dto.CityDTO;
import com.closer.citydistance.dto.SightDTO;
import com.closer.citydistance.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CityService {
    private final CityRepository cityRepository;
    private final CacheMap<Long, City> cityCache;

    public CityDTO find(Long cityId) {
        City city = cityCache.get(cityId);
        if (city != null) return Mapper.cityToDTO(city);
        city = cityRepository.findById(cityId).orElse(null);
        if (city != null) cityCache.put(city.getId(), city);
        return Mapper.cityToDTO(city);
    }


    public List<CityDTO> getAll(PageRequest pageRequest) {
        return cityRepository.findAll(pageRequest).getContent()
                .stream().map(Mapper::cityToDTO).toList();
    }

    public City add(City city) {
        return cityRepository.save(city);
    }

    public void remove(Long cityId) {
        cityCache.remove(cityId);
        cityRepository.deleteById(cityId);
    }

    public List<CityDTO> findByNameAndCountry(String name, String country, PageRequest pageRequest) {
        return cityRepository.findCityByNameAndCountry(name, country, pageRequest).getContent()
                .stream().map(Mapper::cityToDTO).toList();
    }

    public List<CityDTO> findCityBySightName(String sightName, PageRequest pageRequest) {
        return cityRepository.findCityBySightName(sightName, pageRequest)
                .stream().map(Mapper::cityToDTO).toList();
    }

    public List<SightDTO> getSights(Long cityId, PageRequest pageRequest) {
        City city = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException("City not found"));
        int firstElement = (int) pageRequest.getOffset();
        int lastElement = firstElement + pageRequest.getPageSize();
        if(firstElement >= city.getSights().size()) return List.of();
        if(lastElement >= city.getSights().size()) lastElement = city.getSights().size();
        return city.getSights().subList(firstElement,lastElement)
                .stream().map(Mapper::sightToDTO).toList();
    }

    public void update(Long cityId, City city) {
        if (!cityRepository.existsById(cityId))
            throw new DataIntegrityViolationException("City " + cityId + " not found");
        city.setId(cityId);
        cityCache.replace(cityId, city);
        cityRepository.save(city);
    }
}