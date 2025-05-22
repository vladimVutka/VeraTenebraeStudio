package org.example.Controllers;

import org.example.dto.AuthDto;
import org.example.dto.AuthReturnDto;
import org.example.dto.UserDto;
import org.example.userScript.AuthService;
import org.example.userScript.ImageUploadResponse;
import org.example.userScript.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthReturnDto> login(@Valid @RequestBody AuthDto authDto){
        AuthReturnDto authReturnDto = authService.login(authDto);
        return ResponseEntity.ok(authReturnDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        try {
            UserEntity registeredUser = authService.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/upload-image")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            String imageUrl = "/uploads/" + fileName;
            return ResponseEntity.ok(new ImageUploadResponse(imageUrl));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}