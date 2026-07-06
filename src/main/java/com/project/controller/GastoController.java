package com.project.controller;

import com.project.dto.GastoRequest;
import com.project.model.Gasto;
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
    public ResponseEntity<List<Gasto>> getAll(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(gastoService.getAllByUsuarioId(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Gasto> create(@Valid @RequestBody GastoRequest request) {
        return ResponseEntity.ok(gastoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gasto> update(@PathVariable Long id, @Valid @RequestBody GastoRequest request) {
        return ResponseEntity.ok(gastoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gastoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
