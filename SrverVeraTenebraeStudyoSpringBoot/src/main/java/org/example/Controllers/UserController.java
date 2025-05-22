package org.example.Controllers;

import org.apache.catalina.User;
import org.example.userScript.ImageUploadResponse;
import org.example.userScript.UserEntity;
import org.example.dto.UserDto;
import org.example.userScript.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto request) {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            if (userRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            UserEntity user = new UserEntity();
            user.setFirstname(request.getFirstname());
            user.setMiddlename(request.getMiddlename());
            user.setLastname(request.getLastname());
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setImageUrl(request.getImageUrl());
            UserEntity savedUser = userRepository.save(user);

            UserDto responseDto = new UserDto();
            responseDto.setFirstname(savedUser.getFirstname());


            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping
    @CrossOrigin(origins = "*")
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/username/{username}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}