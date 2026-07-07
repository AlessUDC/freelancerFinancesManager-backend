package com.project.service;

import com.project.dto.SuscripcionRequest;
import com.project.model.Suscripcion;
import com.project.model.Usuario;
import com.project.repository.SuscripcionRepository;
import com.project.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuscripcionService {

    private final SuscripcionRepository suscripcionRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Suscripcion> getAllByUsuarioId(Long usuarioId) {
        return suscripcionRepository.findByUsuarioId(usuarioId);
    }

    public Suscripcion create(SuscripcionRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUsuarioId()));

        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setServicio(request.getServicio());
        suscripcion.setMonto(request.getMonto());
        suscripcion.setMoneda(request.getMoneda());
        suscripcion.setCiclo(request.getCiclo() != null ? request.getCiclo() : "MENSUAL");
        suscripcion.setProximaRenovacion(request.getProximaRenovacion());
        suscripcion.setStatus(request.getStatus() != null ? request.getStatus() : "ACTIVA");
        suscripcion.setUsuario(usuario);

        return suscripcionRepository.save(suscripcion);
    }

    public Suscripcion update(Long id, SuscripcionRequest request, Long requestingUserId) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));

        // Verificar que la suscripción pertenece al usuario autenticado
        if (!suscripcion.getUsuario().getId().equals(requestingUserId)) {
            throw new AccessDeniedException("No tienes permiso para modificar esta suscripción");
        }

        if (request.getServicio() != null) suscripcion.setServicio(request.getServicio());
        if (request.getMonto() != null) suscripcion.setMonto(request.getMonto());
        if (request.getMoneda() != null) suscripcion.setMoneda(request.getMoneda());
        if (request.getCiclo() != null) suscripcion.setCiclo(request.getCiclo());
        if (request.getProximaRenovacion() != null) suscripcion.setProximaRenovacion(request.getProximaRenovacion());
        if (request.getStatus() != null) suscripcion.setStatus(request.getStatus());

        return suscripcionRepository.save(suscripcion);
    }

    public void delete(Long id, Long requestingUserId) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));

        // Verificar que la suscripción pertenece al usuario autenticado
        if (!suscripcion.getUsuario().getId().equals(requestingUserId)) {
            throw new AccessDeniedException("No tienes permiso para eliminar esta suscripción");
        }

        suscripcionRepository.delete(suscripcion);
    }
}

