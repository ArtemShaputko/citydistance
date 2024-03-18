package com.closer.citydistance.controller;

import com.closer.citydistance.model.User;
import com.closer.citydistance.dto.CityDTO;
import com.closer.citydistance.dto.UserDTO;
import com.closer.citydistance.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(@RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
                                                @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok().body(userService.getAll(PageRequest.of(pageNumber, pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<List<UserDTO>> getCache() {
        try {
            return ResponseEntity.ok().body(userService.getCache());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody User user) {
        try {
            userService.add(user);
            return ResponseEntity.ok().body("User saved");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam(name = "id") Long userId) {
        try {
            userService.remove(userId);
            return ResponseEntity.ok().body("User removed");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find")
    public ResponseEntity<UserDTO> findById(@RequestParam(name = "id") Long userId) {
        try {
            return ResponseEntity.ok().body(userService.findById(userId));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/liked_cities")
    public ResponseEntity<List<CityDTO>> getLikedCities(
            @RequestParam(name = "id") Long userId,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok().body(userService.getLikedCities(userId, PageRequest.of(pageNumber,pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam(name = "id") Long userId,
                                         @RequestBody User user) {
        try {
            userService.update(userId, user);
            return ResponseEntity.ok().body("User updated");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/liked_cities_country")
    public ResponseEntity<List<UserDTO>> getUsersLikedCitiesWithCountry(
            @RequestParam(name = "country") String countryName,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok().body(userService.getUsersLikedCitiesWithCountry(countryName,
                    PageRequest.of(pageNumber, pageSize)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/like")
    public ResponseEntity<String> setCityLiked(@RequestParam(name = "user_id") Long userId,
                                               @RequestParam(name = "city_id") Long cityId) {
        try {
            userService.setCityLiked(userId, cityId);
            return ResponseEntity.ok().body("City liked");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/remove_like")
    public ResponseEntity<String> removeLike(@RequestParam(name = "user_id") Long userId,
                                             @RequestParam(name = "city_id") Long cityId) {
        try {
            userService.removeLike(userId, cityId);
            return ResponseEntity.ok().body("Like removed");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
