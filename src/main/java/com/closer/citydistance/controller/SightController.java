package com.closer.citydistance.controller;

import com.closer.citydistance.entity.SightEntity;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.service.SightService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/city/sights")
public class SightController {
    private SightService sightService;
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestParam Long cityId, @RequestBody SightEntity sight){
        try {
            sightService.add(cityId, sight);
            return ResponseEntity.ok().body("Sight saved");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/find")
    public ResponseEntity<Sight> find(@RequestParam Long id){
        try {
            return ResponseEntity.ok().body(sightService.find(id));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam Long id){
        try {
            sightService.remove(id);
            return ResponseEntity.ok().body("Sight removed");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
