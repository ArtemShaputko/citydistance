package com.closer.citydistance.controller;

import com.closer.citydistance.entity.UserEntity;
import com.closer.citydistance.model.City;
import com.closer.citydistance.model.User;
import com.closer.citydistance.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    final UserService userService;
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll()
    {
        try {
            return ResponseEntity.ok().body(userService.getAll());
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UserEntity user){
        try {
            userService.add(user);
            return ResponseEntity.ok().body("User saved");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam Long id){
        try {
            userService.remove(id);
            return ResponseEntity.ok().body("User removed");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/find")
    public ResponseEntity<User> findById(@RequestParam(name="id") Long userId){
        try {
            return ResponseEntity.ok().body(userService.findById(userId));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/liked_cities")
    public ResponseEntity<List<City>> getLikedCities(@RequestParam(name="id") Long userId){
        try {
            return ResponseEntity.ok().body(userService.getLikedCities(userId));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> update( @RequestParam(name = "id") Long userId,
                                    @RequestBody UserEntity user){
        try {
           userService.update(userId, user);
           return ResponseEntity.ok().body("User updated");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/like")
    public ResponseEntity<String> likeCity(@RequestParam(name="user_id") Long userId,
                                      @RequestParam(name="city_id") Long cityId){
        try {
            userService.setLikeCity(userId, cityId);
            return ResponseEntity.ok().body("City liked");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
