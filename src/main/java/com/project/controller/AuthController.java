package com.project.controller;

import com.project.dto.LoginRequest;
import com.project.dto.LoginResponse;
import com.project.dto.RegisterRequest;
import com.project.security.SecurityUtils;
import com.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.registrar(request);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        // Verificar que el usuario solo puede ver su propio perfil
        if (!id.equals(SecurityUtils.getCurrentUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permiso para ver el perfil de otro usuario");
        }
        try {
            return ResponseEntity.ok(authService.obtenerPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody com.project.dto.UpdateUserRequest request) {
        // Verificar que el usuario solo puede actualizar su propio perfil
        if (!id.equals(SecurityUtils.getCurrentUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permiso para modificar el perfil de otro usuario");
        }
        try {
            return ResponseEntity.ok(authService.actualizar(id, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        // Verificar que el usuario solo puede eliminar su propia cuenta
        if (!id.equals(SecurityUtils.getCurrentUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permiso para eliminar la cuenta de otro usuario");
        }
        try {
            authService.eliminar(id);
            return ResponseEntity.ok("Cuenta eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

