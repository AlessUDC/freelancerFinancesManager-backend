package com.project.controller;

import com.project.dto.GastoRequest;
import com.project.model.Gasto;
import com.project.security.SecurityUtils;
import com.project.service.GastoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")

@RequiredArgsConstructor
public class GastoController {

    private final GastoService gastoService;

    @GetMapping
    public ResponseEntity<List<Gasto>> getAll() {
        // El usuarioId se extrae del JWT, nunca del cliente
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return ResponseEntity.ok(gastoService.getAllByUsuarioId(currentUserId));
    }

    @PostMapping
    public ResponseEntity<Gasto> create(@Valid @RequestBody GastoRequest request) {
        // Forzar el usuarioId del JWT, ignorando cualquier valor enviado en el body
        request.setUsuarioId(SecurityUtils.getCurrentUserId());
        return ResponseEntity.ok(gastoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gasto> update(@PathVariable Long id, @Valid @RequestBody GastoRequest request) {
        return ResponseEntity.ok(gastoService.update(id, request, SecurityUtils.getCurrentUserId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gastoService.delete(id, SecurityUtils.getCurrentUserId());
        return ResponseEntity.noContent().build();
    }
}

