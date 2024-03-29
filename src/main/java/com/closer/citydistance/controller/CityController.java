package com.closer.citydistance.controller;

import com.closer.citydistance.model.City;
import com.closer.citydistance.dto.CityDTO;
import com.closer.citydistance.dto.SightDTO;
import com.closer.citydistance.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {
    final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAll(
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok().body(cityService.getAll(PageRequest.of(pageNumber,pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/find")
    public ResponseEntity<CityDTO> find(@RequestParam(name = "id") Long cityId) {
        try {
            return ResponseEntity.ok().body(cityService.find(cityId));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody City city) {
        try {
            cityService.add(city);
            return ResponseEntity.ok().body("City added");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam(name = "id") Long cityId) {
        try {
            cityService.remove(cityId);
            return ResponseEntity.ok().body("City removed");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find_by_name_country")
    public ResponseEntity<List<CityDTO>> findByNameAndCountry(
            @RequestParam String name,
            @RequestParam String country,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok()
                    .body(cityService.findByNameAndCountry(name, country, PageRequest.of(pageNumber,pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/find_by_sight_name")
    public ResponseEntity<List<CityDTO>> findCityBySightName(
            @RequestParam(name = "name") String sightName,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok().body(cityService.findCityBySightName(sightName, PageRequest.of(pageNumber,pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get_sights")
    public ResponseEntity<List<SightDTO>> getSights(
            @RequestParam(name = "id") Long cityId,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok().body(cityService.getSights(cityId, PageRequest.of(pageNumber,pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam(name = "id") Long cityId,
                                         @RequestBody City city) {
        try {
            cityService.update(cityId, city);
            return ResponseEntity.ok().body("City updated");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

