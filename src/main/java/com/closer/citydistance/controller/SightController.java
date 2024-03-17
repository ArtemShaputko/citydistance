package com.closer.citydistance.controller;

import com.closer.citydistance.model.Sight;
import com.closer.citydistance.dto.SightDTO;
import com.closer.citydistance.service.SightService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/city/sights")
public class SightController {
    private SightService sightService;

    @GetMapping("/all")
    public ResponseEntity<List<SightDTO>> getAll(
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize
    ) {
        try {
            return ResponseEntity.ok().body(sightService.getAll(PageRequest.of(pageNumber,pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestParam(name = "city_id") Long cityId, @RequestBody Sight sight) {
        try {
            sightService.add(cityId, sight);
            return ResponseEntity.ok().body("Sight saved");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find")
    public ResponseEntity<SightDTO> find(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(sightService.find(id));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam Long id) {
        try {
            sightService.remove(id);
            return ResponseEntity.ok().body("Sight removed");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam(name = "id") Long sightId,
                                         @RequestBody Sight sight) {
        try {
            sightService.update(sightId, sight);
            return ResponseEntity.ok().body("Sight updated");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
