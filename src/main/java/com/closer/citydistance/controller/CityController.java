package com.closer.citydistance.controller;

import com.closer.citydistance.entity.CityEntity;
import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {
    final CityService cityService;
    @GetMapping("/all")
    public ResponseEntity<List<City>> getAll()
    {
        try {
            return ResponseEntity.ok().body(cityService.getAll());
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CityEntity city){
        try {
            cityService.add(city);
            return ResponseEntity.ok().body("City added");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam String name,
                       @RequestParam String country){
        try {
            cityService.remove(name, country);
            return  ResponseEntity.ok().body("City removed");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/find")
    public ResponseEntity<List<City>> find(@RequestParam String name,
                                  @RequestParam String country){
        try {
            return ResponseEntity.ok().body(cityService.find(name, country));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/get_sights")
    public ResponseEntity<List<Sight>> getSights(@RequestParam(name = "id") Long cityId){
        try{
            return ResponseEntity.ok().body(cityService.getSights(cityId));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam(name = "id") Long cityId,
                                    @RequestBody CityEntity city){
        try {
            cityService.update(cityId, city);
            return ResponseEntity.ok().body("City updated");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

