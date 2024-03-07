package com.closer.citydistance.controller;

import com.closer.citydistance.entity.CityEntity;
import com.closer.citydistance.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {
    final CityService cityService;
    @GetMapping("/all")
    public ResponseEntity<?> getAll()
    {
        try {
            return ResponseEntity.ok().body(cityService.getAll());
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CityEntity city){
        try {
            cityService.add(city);
            return ResponseEntity.ok().body("City added");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam String name,
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
    public ResponseEntity<?> find(@RequestParam String name,
                                  @RequestParam String country){
        try {
            return ResponseEntity.ok().body(cityService.find(name, country));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get_sights")
    public ResponseEntity<?> getSights(@RequestParam(name = "id") Long cityId){
        try{
            return ResponseEntity.ok().body(cityService.getSights(cityId));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam(name = "id") Long cityId,
                                    @RequestBody CityEntity city){
        try {
            return ResponseEntity.ok().body(cityService.update(cityId, city));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

