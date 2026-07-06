package com.project.service;

import com.project.dto.IngresoRequest;
import com.project.model.Ingreso;
import com.project.model.Usuario;
import com.project.repository.IngresoRepository;
import com.project.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngresoService {

    private final IngresoRepository ingresoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Ingreso> getAllByUsuarioId(Long usuarioId) {
        return ingresoRepository.findByUsuarioId(usuarioId);
    }

    public Ingreso create(IngresoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUsuarioId()));

        Ingreso ingreso = new Ingreso();
        ingreso.setProyectoNombre(request.getProyectoNombre());
        ingreso.setMontoBruto(request.getMontoBruto());
        ingreso.setRetencion(request.getRetencion() != null ? request.getRetencion() : BigDecimal.ZERO);
        ingreso.setMontoNeto(request.getMontoNeto());
        ingreso.setMoneda(request.getMoneda());
        ingreso.setStatus(request.getStatus() != null ? request.getStatus() : "PENDIENTE");
        ingreso.setFecha(request.getFecha());
        ingreso.setFechaEmision(request.getFechaEmision());
        ingreso.setFechaVencimiento(request.getFechaVencimiento());
        ingreso.setUsuario(usuario);

        return ingresoRepository.save(ingreso);
    }

    public Ingreso update(Long id, IngresoRequest request) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + id));

        ingreso.setProyectoNombre(request.getProyectoNombre());
        ingreso.setMontoBruto(request.getMontoBruto());
        ingreso.setRetencion(request.getRetencion() != null ? request.getRetencion() : BigDecimal.ZERO);
        ingreso.setMontoNeto(request.getMontoNeto());
        ingreso.setMoneda(request.getMoneda());
        ingreso.setStatus(request.getStatus() != null ? request.getStatus() : "PENDIENTE");
        ingreso.setFecha(request.getFecha());
        ingreso.setFechaEmision(request.getFechaEmision());
        ingreso.setFechaVencimiento(request.getFechaVencimiento());

        return ingresoRepository.save(ingreso);
    }

    public void delete(Long id) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + id));
        ingresoRepository.delete(ingreso);
    }

    public Ingreso updateStatus(Long id, String status) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + id));
        ingreso.setStatus(status);
        return ingresoRepository.save(ingreso);
    }
}
