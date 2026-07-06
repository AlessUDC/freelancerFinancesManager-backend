package com.project.controller;

import com.project.dto.SuscripcionRequest;
import com.project.model.Suscripcion;
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
    public ResponseEntity<List<Suscripcion>> getAll(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(suscripcionService.getAllByUsuarioId(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Suscripcion> create(@Valid @RequestBody SuscripcionRequest request) {
        return ResponseEntity.ok(suscripcionService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suscripcion> update(@PathVariable Long id, @RequestBody SuscripcionRequest request) {
        return ResponseEntity.ok(suscripcionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        suscripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
