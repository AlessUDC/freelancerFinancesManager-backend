package com.project.controller;

import com.project.dto.SuscripcionRequest;
import com.project.model.Suscripcion;
import com.project.security.SecurityUtils;
import com.project.service.SuscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suscripciones")

@RequiredArgsConstructor
public class SuscripcionController {

    private final SuscripcionService suscripcionService;

    @GetMapping
    public ResponseEntity<List<Suscripcion>> getAll() {
        // El usuarioId se extrae del JWT, nunca del cliente
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return ResponseEntity.ok(suscripcionService.getAllByUsuarioId(currentUserId));
    }

    @PostMapping
    public ResponseEntity<Suscripcion> create(@Valid @RequestBody SuscripcionRequest request) {
        // Forzar el usuarioId del JWT, ignorando cualquier valor enviado en el body
        request.setUsuarioId(SecurityUtils.getCurrentUserId());
        return ResponseEntity.ok(suscripcionService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suscripcion> update(@PathVariable Long id, @RequestBody SuscripcionRequest request) {
        return ResponseEntity.ok(suscripcionService.update(id, request, SecurityUtils.getCurrentUserId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        suscripcionService.delete(id, SecurityUtils.getCurrentUserId());
        return ResponseEntity.noContent().build();
    }
}

