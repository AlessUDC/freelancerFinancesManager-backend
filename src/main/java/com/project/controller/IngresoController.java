package com.project.controller;

import com.project.dto.IngresoRequest;
import com.project.model.Ingreso;
import com.project.service.IngresoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingresos")

@RequiredArgsConstructor
public class IngresoController {

    private final IngresoService ingresoService;

    @GetMapping
    public ResponseEntity<List<Ingreso>> getAll(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(ingresoService.getAllByUsuarioId(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Ingreso> create(@Valid @RequestBody IngresoRequest request) {
        return ResponseEntity.ok(ingresoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingreso> update(@PathVariable Long id, @Valid @RequestBody IngresoRequest request) {
        return ResponseEntity.ok(ingresoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingresoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Ingreso> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok(ingresoService.updateStatus(id, status));
    }
}
