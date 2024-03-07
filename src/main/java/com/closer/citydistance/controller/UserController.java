package com.closer.citydistance.controller;

import com.closer.citydistance.entity.UserEntity;
import com.closer.citydistance.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    final UserService userService;
    @GetMapping("/all")
    public ResponseEntity<?> getAll()
    {
        try {
            return ResponseEntity.ok().body(userService.getAll());
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UserEntity user){
        try {
            userService.add(user);
            return ResponseEntity.ok().body("User saved");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam Long id){
        try {
            userService.remove(id);
            return ResponseEntity.ok().body("User removed");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findById(@RequestParam(name="id") Long userId){
        try {
            return ResponseEntity.ok().body(userService.findById(userId));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/liked_cities")
    public ResponseEntity<?> getLikedCities(@RequestParam(name="id") Long userId){
        try {
            return ResponseEntity.ok().body(userService.getLikedCities(userId));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> update( @RequestParam(name = "id") Long userId,
                                    @RequestBody UserEntity user){
        try {
            return ResponseEntity.ok().body(userService.update(userId, user));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/like")
    public ResponseEntity<?> likeCity(@RequestParam(name="user_id") Long userId,
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
